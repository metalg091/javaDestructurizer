#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <cstdlib>
#include <utility>
#include <tuple>
#include <set>

using namespace std; // for some reason it started to require std:: but don't remove, it could break the code

// declarations:
vector<string> split(string s, string delimiter);
string protlvl(string line);
string removeQuotes(string line);
string typeMaker(string type);
pair<string, string> typeNameSeparator(string line);
int createVar(fstream *infile, ofstream *outfile, string line);
int createMethod(fstream *infile, ofstream *outfile, string line);
int createConstructor(fstream *infile, ofstream *outfile, string line, string name);
string concat(vector<string> v, char delimiter);
std::tuple<ofstream *, string, string, string> createFile(string line);

// I know its bad practice but this far into the project I'm not passing arguments through twelve functions
bool isInterface = false;
set<string> imports;

vector<string> split(string s, string delimiter)
{
    vector<string> result;
    size_t pos = 0;
    while ((pos = s.find(delimiter)) != string::npos)
    {
        result.push_back(s.substr(0, pos));
        s.erase(0, pos + delimiter.length());
    }
    result.push_back(s);
    return result;
}

string protlvl(string line) // 1 == true, 0 == false
{
    // search for VISIBLE_TO_ALL VISIBLE_TO_SUBCLASSES VISIBLE_TO_NONE
    if (line.find("VISIBLE_TO_ALL") != string::npos)
    {
        return "public ";
    }
    else if (line.find("VISIBLE_TO_SUBCLASSES") != string::npos)
    {
        return "protected ";
    }
    else if (line.find("VISIBLE_TO_NONE") != string::npos)
    {
        return "private ";
    }
    else
    {
        return "";
    }
}

string removeQuotes(string line)
{
    size_t pos = line.find('"');
    if (pos == string::npos)
    {
        return line;
    }
    return removeQuotes(line.substr(0, pos) + line.substr(pos + 1));
}

// check for list and arrays (never seen a set or map in a test) => there is a map in one of the tests
string typeMaker(string type)
{
    if (type.find(')') != string::npos)
    {
        type = type.substr(0, type.find(')'));
    }
    // don't need to write List or HashSet, they are simple types
    if (type.find("vararg of ") != string::npos)
    {
        type = type.substr(type.find("of ") + 3) + "...";
    }
    else if (type.find("array of ") != string::npos)
    {
        type = type.substr(type.find("of ") + 3) + "[]";
    }
    else if (type.find("HashMap of ") != string::npos)
    {
        imports.insert("java.util.*");
        string temp = type.substr(type.find("of ") + 3);
        string key = temp.substr(0, temp.find(" to "));
        string value = temp.substr(temp.find(" to ") + 4);
        type = "HashMap<" + key + ", " + value + ">";
    }
    else if (type.find("of ") != string::npos) // try to autocomplete unimplemented types
    {
        imports.insert("java.util.*");
        type = type.substr(0, type.find("of ") - 1) + "<" + type.substr(type.find("of ") + 3) + ">";
    }
    if (type.find("of ") != string::npos)
    {
        if (type.find("...") != string::npos)
        {
            return typeMaker(type.substr(0, type.find("..."))) + "...";
        }
        if (type.find("<") != string::npos && type.find(">") != string::npos)
        {
            if (type.find(",") != string::npos)
            {
                string key = type.substr(0, type.find(","));
                key = typeMaker(key.substr(key.find("<") + 1));
                string value = type.substr(type.find(",") + 2);
                value = typeMaker(value.substr(0, value.find(">")));
                return removeQuotes(type.substr(0, type.find("<") + 1) + key + ", " + value + type.substr(type.find(">")));
            }
            string temp = type.substr(type.find("<"));
            temp = temp.substr(1, temp.find(">") - 1);
            return removeQuotes(type.substr(0, type.find("<") + 1) + typeMaker(temp) + type.substr(type.find(">")));
        }
        else if (type.find("array of ") != string::npos)
        {
            return typeMaker(type.substr(type.find("of ") + 3) + "[]");
        }
    }
    else if (type.find(".") != string::npos && type.find("...") == string::npos)
    {
        string imp = type.substr(0, type.find_last_of('.'));
        imports.insert(imp);
        type = type.substr(type.find_last_of('.') + 1);
    }
    return removeQuotes(type);
}

pair<string, string> typeNameSeparator(string line)
{
    string type;
    string name;
    if (line.find("ofType") != string::npos)
    {
        // structure => it.hasField("defaultAuthor", ofType("String"))
        name = line.substr(line.find('"') + 1);
        name = name.substr(0, name.find('"'));
        type = line.substr(line.find("ofType(\"") + 8);
        type = type.substr(0, type.find("\")"));
        return make_pair(typeMaker(type), removeQuotes(name));
    }
    else
    {
        if (line.find(':') != string::npos)
        {
            type = line.substr(line.find(':') + 2, line.find(')') - line.find(':') - 3);
            name = line.substr(line.find('"') + 1, line.find(':') - line.find('"') - 1);
            return make_pair(typeMaker(type), removeQuotes(name));
        }
        else
        {
            return make_pair(typeMaker(line), "todoName");
        }
    }
}

int createVar(fstream *infile, ofstream *outfile, string line)
{
    string vis = "";
    string Static = "";
    string Final = "";
    // structure -> ("name: type")
    // auto [type, name] = typeNameSeparator(line); // only works after c++11
    pair<string, string> temp = typeNameSeparator(line);
    string type = temp.first;
    string name = temp.second;
    if (line.find("hasFieldOfType") != string::npos)
    {
        name = line.substr(line.find('"') + 1);
        name = name.substr(0, name.find('"'));
        type = line.substr(0, line.find_last_of('"'));
        type = type.substr(type.find(',') + 3);
    }
    getline(*infile, line);
    // search for visibility
    vis = protlvl(line);
    if (line.find("USABLE_WITHOUT_INSTANCE") != string::npos)
    {
        Static = "static ";
    }
    if (line.find("NOT_MODIFIABLE") != string::npos)
    {
        Final = "final ";
    }
    type = typeMaker(type);
    *outfile << "\t" << vis << Static << Final << type << " " << name << ";\n";
    getline(*infile, line);
    if (line.find("thatHas(") != string::npos)
    {
        bool hasGetter = false;
        bool hasSetter = false;
        if (line.find("GETTER") != string::npos)
        {
            hasGetter = true;
        }
        if (line.find("SETTER") != string::npos)
        {
            hasSetter = true;
        }
        if (hasGetter)
        {
            *outfile << "\n\t" << "public " << type << " get" << static_cast<char>(toupper(name[0])) << name.substr(1) << "() {\n";
            *outfile << "\t\treturn " << name << ";\n";
            *outfile << "\t}\n";
        }
        if (hasSetter)
        {
            *outfile << "\n\t" << "public void set" << static_cast<char>(toupper(name[0])) << name.substr(1) << "(" << type << " " << name << ") {\n";
            *outfile << "\t\t// TODO\n";
            *outfile << "\t\tthis." << name << " = " << name << ";\n";
            *outfile << "\t}\n";
        }
    }
    return 0;
}

int createMethod(fstream *infile, ofstream *outfile, string line)
{
    string returnType;
    vector<string> paramTypes;
    vector<string> paramNames;
    string name;
    string vis = "";
    string Static = "";
    name = line.substr(line.find('"') + 1);
    name = name.substr(0, name.find('"'));
    if (line.find("withParams") != string::npos)
    {
        string temp = line.substr(line.find("withParams(") + 11);
        temp = temp.substr(0, temp.find(")"));
        vector<string> tempV = split(temp, ", ");
        for (size_t i = 0; i < tempV.size(); i++)
        {
            // auto [temptype, tempname] = typeNameSeparator(tempV[i]); // only works after c++11
            pair<string, string> tempPair = typeNameSeparator(tempV[i]);
            string temptype = tempPair.first;
            string tempname = tempPair.second;
            if (tempname == "todoName")
            {
                tempname = "todoName" + to_string(i);
            }
            paramTypes.push_back(temptype);
            paramNames.push_back(tempname);
        }
    }
    else if (line.find("hasMethodWithParams") != string::npos)
    {
        string temp = line.substr(line.find(',') + 2);
        vector<string> tempV = split(temp, ", ");
        for (size_t i = 0; i < tempV.size(); i++)
        {
            paramTypes.push_back(typeMaker(tempV[i]));
            paramNames.push_back("todoName" + to_string(i));
        }
    } // else no params
    for (int i = 0; i < 2; i++)
    {
        getline(*infile, line);
        if (line.find("thatIs(") != string::npos)
        {
            vis = protlvl(line);
            if (line.find("USABLE_WITHOUT_INSTANCE") != string::npos)
            {
                Static = "static ";
            }
        }
        else if (line.find("thatReturns") != string::npos)
        {
            if (line.find("thatReturnsNothing") != string::npos)
            {
                returnType = "void";
            }
            else
            {
                returnType = line.substr(line.find("thatReturns(\"") + 13);
                returnType = returnType.substr(0, returnType.find("\")"));
                returnType = typeMaker(returnType);
            }
        }
    }
    *outfile << "\t" << vis << Static << returnType << " " << name << "(";
    for (size_t i = 0; i < paramTypes.size(); i++)
    {
        *outfile << paramTypes[i] << " " << paramNames[i];
        if (i != paramTypes.size() - 1)
        {
            *outfile << ", ";
        }
    }
    *outfile << ")";
    if (isInterface) // interfaces don't have bodies
    {
        *outfile << ";\n";
        return 0;
    }
    *outfile << " {\n";
    *outfile << "\t\t// TODO\n";
    if (returnType != "void")
    {
        if (returnType == "int")
        {
            *outfile << "\t\treturn 0;\n";
        }
        else if (returnType == "double")
        {
            *outfile << "\t\treturn 0.0;\n";
        }
        else if (returnType == "float")
        {
            *outfile << "\t\treturn 0.0f;\n";
        }
        else if (returnType == "long")
        {
            *outfile << "\t\treturn 0L;\n";
        }
        else if (returnType == "char")
        {
            *outfile << "\t\treturn '0';\n";
        }
        else if (returnType == "byte")
        {
            *outfile << "\t\treturn (byte)0;\n";
        }
        else if (returnType == "short")
        {
            *outfile << "\t\treturn (short)0;\n";
        }
        else if (returnType == "boolean")
        {
            *outfile << "\t\treturn false;\n";
        }
        else
        {
            *outfile << "\t\treturn null;\n";
        }
    }
    *outfile << "\t}\n";
    return 0;
}

int createConstructor(fstream *infile, ofstream *outfile, string line, string name)
{
    vector<string> paramTypes;
    vector<string> paramNames;
    string vis = "";
    if (line.find("withArgs") != string::npos)
    {
        if (line.find("withArgsAsInParent") != string::npos)
        {
            cout << "**WARNING** Constructor withArgsAsInParent\n";
            paramTypes.push_back("withArgsAsInParent");
            paramNames.push_back("withArgsAsInParent");
        }
        else if (line.find("withArgsSimilarToFields") != string::npos)
        {
            cout << "**WARNING** Constructor withArgsSimilarToFields\n";
            paramTypes.push_back("withArgsSimilarToFields");
            paramNames.push_back("withArgsSimilarToFields");
        }
        else if (line.find("withArgs(") != string::npos)
        {
            string temp = line.substr(line.find("withArgs(") + 9);
            temp = temp.substr(0, temp.find(")"));
            vector<string> tempV = split(temp, ", ");
            for (size_t i = 0; i < tempV.size(); i++)
            {
                // auto [temptype, tempname] = typeNameSeparator(tempV[i]); // only works after c++11
                pair<string, string> tempPair = typeNameSeparator(tempV[i]);
                string temptype = tempPair.first;
                string tempname = tempPair.second;
                paramTypes.push_back(temptype);
                paramNames.push_back(tempname);
            }
        }
        else
        {
            cout << "**WARNING** Constructor with unimplemented args type\n";
            paramTypes.push_back("unimplemented");
            paramNames.push_back("unimplemented");
        }
    } // else no params
    getline(*infile, line);
    vis = protlvl(line);
    *outfile << "\t" << vis << name << "(";
    for (size_t i = 0; i < paramTypes.size(); i++)
    {
        *outfile << paramTypes[i] << " " << paramNames[i];
        if (i != paramTypes.size() - 1)
        {
            *outfile << ", ";
        }
    }
    *outfile << ") {\n";
    *outfile << "\t\t// TODO\n";
    *outfile << "\t}\n";
    return 0;
}

string concat(vector<string> v, char delimiter)
{
    string result;
    for (size_t i = 0; i < v.size(); i++)
    {
        result += v[i];
        if (i != v.size() - 1)
        {
            result += delimiter;
        }
    }
    return result;
}

std::tuple<ofstream *, string, string, string> createFile(string line)
{
    size_t pos = line.find('"');
    size_t pos2 = line.find('"', pos + 1);
    if (pos == string::npos || pos2 == string::npos)
    {
        cout << "Error in createFile\n";
        exit(1);
    }
    string className = line.substr(pos + 1, pos2 - pos - 1);
    pos = className.find_last_of('.');
    string name = className.substr(pos + 1);
    vector<string> package = split(className.substr(0, pos), ".");
    // create file
    string place;
    for (size_t i = 0; i < package.size(); i++)
    {
        place += package[i];
        place += "/";
    }
    string command = "mkdir -p " + place;
    system(command.c_str());
    place += name;
    place += ".java";
    ofstream *file = new ofstream(place);
    if (!file)
    {
        std::cout << "File error if ya here, I can't help ya!" << endl;
        exit(1);
    }
    *file << "package ";
    for (size_t i = 0; i < package.size(); i++)
    {
        *file << package[i];
        if (i != package.size() - 1)
        {
            *file << ".";
        }
    }
    *file << ";\n";
    return make_tuple(file, name, concat(package, '.'), place);
}

int main(int argc, char **args)
{
    if (argc != 2)
    {
        cout << "Usage: " << args[0] << " <input file>" << endl;
        exit(1);
    }
    fstream file(args[1]);
    string line;
    string stopString = "CheckThat.the";
    string outfilename;
    if (file.is_open())
    {
        bool go = true;
        while (go && getline(file, line)) // first bool else it will skip the first line
        {
            if (line.find(stopString) != string::npos)
            {
                go = false;
            }
        }
        size_t pos; // backwards compatibility, I'm not rewriting the code
        // auto [sfile, name, package] = createFile(line); // only works after c++11
        tuple<ofstream *, string, string, string> tempTuple = createFile(line);
        ofstream *sfile = get<0>(tempTuple);
        string name = get<1>(tempTuple);
        string package = get<2>(tempTuple);
        outfilename = get<3>(tempTuple);
        if (line.find("theClass") != string::npos)
        {
            string parent = "";
            string interface = "";
            // search for withInterface or withParent in line
            string s = "withParent(\"";
            pos = line.find(s);
            if (pos != string::npos)
            {
                parent = " extends ";
                string tempparent = line.substr(pos + s.length());
                pos = tempparent.find('"');
                tempparent = tempparent.substr(0, pos);
                pos = tempparent.find_last_of('.');
                parent += tempparent.substr(pos + 1);
                tempparent = tempparent.substr(0, pos);
                if (tempparent != package && tempparent.find("of ") == string::npos)
                {
                    imports.insert(tempparent);
                }
                else if (tempparent.find("of ") != string::npos)
                {
                    typeMaker(tempparent); // this will add necessary imports
                }
            }
            s = "withInterface(\"";
            pos = line.find(s);
            if (pos != string::npos)
            {
                interface = " implements ";
                string tempinterface = line.substr(pos + s.length());
                pos = tempinterface.find('"');
                tempinterface = tempinterface.substr(0, pos);
                pos = tempinterface.find_last_of('.');
                interface += tempinterface.substr(pos + 1);
                tempinterface = tempinterface.substr(0, pos);
                if (tempinterface != package)
                {
                    imports.insert(tempinterface);
                }
            }
            getline(file, line);
            string vis = protlvl(line);
            if (parent.find("of ") != string::npos)
            {
                parent = typeMaker(parent);
            }
            *sfile << vis << "class " << name << parent << interface << " {\n";
        }
        else if (line.find("theInterface") != string::npos)
        {
            isInterface = true;
            string parent = "";
            string s = "withParent(\"";
            pos = line.find(s);
            if (pos != string::npos)
            {
                parent = " extends ";
                string tempparent = line.substr(pos + s.length());
                pos = tempparent.find('"');
                tempparent = tempparent.substr(0, pos);
                pos = tempparent.find_last_of('.');
                parent += tempparent.substr(pos + 1);
                tempparent = tempparent.substr(0, pos);
                if (tempparent != package)
                {
                    imports.insert(tempparent);
                }
            }
            getline(file, line);
            string vis = protlvl(line);
            *sfile << vis << "interface " << name << parent << " {\n";
        }
        else if (line.find("theEnum") != string::npos)
        {
            getline(file, line);
            string vis = protlvl(line);
            *sfile << vis << "enum " << name << " {\n";
            getline(file, line);
            if (line.find("hasEnumElements") != string::npos)
            {
                string temp = line.substr(line.find("hasEnumElements(") + 16);
                temp = temp.substr(0, temp.find(")"));
                vector<string> tempV = split(temp, ", ");
                for (size_t i = 0; i < tempV.size(); i++)
                {
                    *sfile << "\t" << removeQuotes(tempV[i]);
                    if (i != tempV.size() - 1)
                    {
                        *sfile << ",\n";
                    }
                }
            }
        }
        else if (line.find("theCheckedException") != string::npos)
        {
            getline(file, line);
            string vis = protlvl(line);
            *sfile << vis << "class " << name << " extends Exception {\n";
        }
        else
        {
            cout << "Unknown type of class\n";
            cout << line << endl;
            exit(1);
        }
        //  search for methods, fields, constructors
        while (getline(file, line))
        {
            if (line.find("it.hasMethod") != string::npos)
            {
                createMethod(&file, sfile, line);
            }
            else if (line.find("it.hasConstructor") != string::npos)
            {
                createConstructor(&file, sfile, line, name);
            }
            else if (line.find("hasNoArgConstructor") != string::npos)
            {
                getline(file, line);
                string vis = protlvl(line);
                *sfile << "\t" << vis << name << "() {\n";
                *sfile << "\t\t// TODO\n";
                *sfile << "\t}\n";
            }
            else if (line.find("it.hasField") != string::npos)
            {
                createVar(&file, sfile, line);
            }
            else if (line.find("it.has(TEXTUAL_REPRESENTATION") != string::npos)
            {
                *sfile << "\t@Override\n";
                *sfile << "\tpublic String toString() {\n";
                *sfile << "\t\treturn \"TODO\";\n";
                *sfile << "\t}\n";
            }
            else if (line.find("has(EQUALITY_CHECK") != string::npos)
            {
                *sfile << "\t@Override\n";
                *sfile << "\tpublic boolean equals(Object obj) {\n";
                *sfile << "\t\t// todo\n";
                *sfile << "\t\treturn true;\n";
                *sfile << "\t}\n";
                // need to overwrite hashCode too
                *sfile << "\t@Override\n";
                *sfile << "\tpublic int hashCode() {\n";
                *sfile << "\t\t// todo\n";
                *sfile << "\t\treturn 1;\n";
                *sfile << "\t}\n";
            }
            else if (line.find("has(HASH_CODE") != string::npos)
            {
                *sfile << "\t@Override\n";
                *sfile << "\tpublic int hashCode() {\n";
                *sfile << "\t\t// todo\n";
                *sfile << "\t\treturn 1;\n";
                *sfile << "\t}\n";
            }
        }
        *sfile << "\n}";
        file.close();
        sfile->close(); // Close the ofstream properly -> VERY IMPORTANT!!! DO NOT REMOVE!!!
        delete sfile;   // Delete the ofstream object to prevent memory leaks
    }
    else
    {
        std::cout << "Unable to open file";
    }
    // Make vector unique

    // Open the output file again to add imports
    fstream file2(outfilename, ios::in);
    vector<string> fileLines;
    if (file2.is_open())
    {
        line = "";
        while (getline(file2, line))
        {
            fileLines.push_back(line);
            if (line.find("package ") != string::npos)
            {
                fileLines.push_back("");
                for (string imp : imports)
                {
                    string temp = "import " + imp + ";";
                    fileLines.push_back(temp);
                }
                fileLines.push_back("");
            }
        }
        file2.close();
    }
    else
    {
        std::cout << "Unable to open file";
    }
    ofstream file3(outfilename);
    for (size_t i = 0; i < fileLines.size(); i++)
    {
        file3 << fileLines[i] << endl;
    }
    return 0;
}
