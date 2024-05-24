#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <cstdlib>
#include <utility>
#include <tuple>

using namespace std; // for some reason it started to require std:: but don't remove, it could break the code

// using capital letters to avoid conflict
enum ProtectionLevel
{
    Public,
    Protected,
    Private,
    Default
};

void testNull(size_t pos)
{
    if (pos == string::npos)
    {
        std::cout << "Not found" << endl;
        exit(1);
    }
}

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

ProtectionLevel protlvl(string line) // 1 == true, 0 == false
{
    // search for VISIBLE_TO_ALL VISIBLE_TO_SUBCLASSES VISIBLE_TO_NONE
    if (line.find("VISIBLE_TO_ALL") != string::npos)
    {
        return Public;
    }
    else if (line.find("VISIBLE_TO_SUBCLASSES") != string::npos)
    {
        return Protected;
    }
    else if (line.find("VISIBLE_TO_NONE") != string::npos)
    {
        return Private;
    }
    else
    {
        return Default;
    }
}

int createVar(fstream *infile, ofstream *outfile, string line)
{
    string type;
    string name;
    string vis = "";
    string Static = "";
    string Final = "";
    // structure -> ("name: type")
    name = line.substr(line.find('"') + 1, line.find(':') - line.find('"') - 1);
    type = line.substr(line.find(':') + 2, line.find(')') - line.find(':') - 3);
    // std::cout << "Found name " << name << endl;
    // std::cout << "Found type " << type << endl;
    getline(*infile, line);
    // search for visibility
    switch (protlvl(line))
    {
    case Public:
        vis = "public ";
        break;
    case Protected:
        vis = "protected ";
        break;
    case Private:
        vis = "private ";
        break;
    case Default:

        break;
    }
    if (line.find("USABLE_WITHOUT_INSTANCE") != string::npos)
    {
        Static = "static ";
    }
    if (line.find("NOT_MODIFIABLE") != string::npos)
    {
        Final = "final ";
    }
    // check for list and arrays (never seen a set or map in a test)
    if (type.find("List") != string::npos)
    {
        type = "List<" + type.substr(type.find("of ") + 3) + ">";
    }
    else if (type.find("array") != string::npos)
    {
        type = type.substr(type.find("of ") + 3) + "[]";
    }
    *outfile << "\t" << vis << Static << Final << type << " " << name << ";\n";
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
        for (int i = 0; i < tempV.size(); i++)
        {
            vector<string> tempV2 = split(tempV[i], ": ");
            string tempVX = tempV2[1].substr(0, tempV2[1].find("\""));
            if (tempVX.find("List") != string::npos)
            {
                tempVX = "List<" + tempVX.substr(tempVX.find("of ") + 3) + ">";
            }
            else if (tempVX.find("array") != string::npos)
            {
                tempVX = tempVX.substr(tempVX.find("of ") + 3) + "[]";
            }
            paramTypes.push_back(tempVX);
            paramNames.push_back(tempV2[0].substr(1, tempV2[0].length()));
        }
    } // else no params
    for (int i = 0; i < 2; i++)
    {
        getline(*infile, line);
        if (line.find("thatIs(") != string::npos)
        {
            switch (protlvl(line))
            {
            case Public:
                vis = "public ";
                break;
            case Protected:
                vis = "protected ";
                break;
            case Private:
                vis = "private ";
                break;
            case Default:
                break;
            }
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
                if (returnType.find("List") != string::npos)
                {
                    returnType = "List<" + returnType.substr(returnType.find("of ") + 3) + ">";
                }
                else if (returnType.find("array") != string::npos)
                {
                    returnType = returnType.substr(returnType.find("of ") + 3) + "[]";
                }
                // std::cout << "Found returnType " << returnType << " " << name << endl;
            }
        }
    }
    *outfile << "\t" << vis << Static << returnType << " " << name << "(";
    for (int i = 0; i < paramTypes.size(); i++)
    {
        *outfile << paramTypes[i] << " " << paramNames[i];
        if (i != paramTypes.size() - 1)
        {
            *outfile << ", ";
        }
    }
    *outfile << ") {\n";
    *outfile << "\t\t// TODO Auto-generated method stub\n";
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
        else
        {
            string temp = line.substr(line.find("withArgs(") + 9);
            temp = temp.substr(0, temp.find(")"));
            vector<string> tempV = split(temp, ", ");
            for (int i = 0; i < tempV.size(); i++)
            {
                vector<string> tempV2 = split(tempV[i], ": ");
                string tempVX = tempV2[1].substr(0, tempV2[1].find("\""));
                if (tempVX.find("List") != string::npos)
                {
                    tempVX = "List<" + tempVX.substr(tempVX.find("of ") + 3) + ">";
                }
                else if (tempVX.find("array") != string::npos)
                {
                    tempVX = tempVX.substr(tempVX.find("of ") + 3) + "[]";
                }
                paramTypes.push_back(tempVX);
                paramNames.push_back(tempV2[0].substr(1, tempV2[0].length()));
            }
        }
    } // else no params
    getline(*infile, line);
    switch (protlvl(line))
    {
    case Public:
        vis = "public ";
        break;
    case Protected:
        vis = "protected ";
        break;
    case Private:
        vis = "private ";
        break;
    case Default:
        break;
    }
    *outfile << "\t" << vis << name << "(";
    for (int i = 0; i < paramTypes.size(); i++)
    {
        *outfile << paramTypes[i] << " " << paramNames[i];
        if (i != paramTypes.size() - 1)
        {
            *outfile << ", ";
        }
    }
    *outfile << ") {\n";
    *outfile << "\t\t// TODO Auto-generated constructor stub\n";
    *outfile << "\t}\n";
    return 0;
}

string concat(vector<string> v, char delimiter)
{
    string result;
    for (int i = 0; i < v.size(); i++)
    {
        result += v[i];
        if (i != v.size() - 1)
        {
            result += delimiter;
        }
    }
    return result;
}

std::tuple<ofstream *, string, string> createFile(string line)
{
    size_t pos = line.find('"');
    testNull(pos);
    size_t pos2 = line.find('"', pos + 1);
    testNull(pos2);
    string className = line.substr(pos + 1, pos2 - pos - 1);
    pos = className.find_last_of('.');
    string name = className.substr(pos + 1);
    vector<string> package = split(className.substr(0, pos), ".");
    /*
    for(int i = 0; i < package.size(); i++){
        std::cout << "package " << package[i] << endl;
    }
    std::cout << "name " << name << endl;
    std::cout << "Found theClass " << className << endl;
    */
    // create file
    string place;
    for (int i = 0; i < package.size(); i++)
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
    for (int i = 0; i < package.size(); i++)
    {
        *file << package[i];
        if (i != package.size() - 1)
        {
            *file << ".";
        }
    }
    *file << ";\n";
    return make_tuple(file, name, concat(package, '.'));
}

int main(int argc, char **args)
{
    fstream file(args[1]);
    string line;
    string stopString = "public static void init";
    // fstream file("VariableStructureTest.java");
    // fstream file("HeuristicStructureTest.java");
    // fstream file("EqualityConstraintStructureTest.java");
    // fstream file("BruteForceHeuristicStructureTest.java");
    // fstream file("ILPSolverStructureTest.java");
    // fstream file("InequalityConstraintStructureTest.java");
    // fstream file("ConstraintStructureTest.java");

    if (file.is_open())
    {
        while (getline(file, line))
        {
            if (line.find(stopString) != string::npos)
            {
                break;
            }
        }
        getline(file, line);
        string properties;
        size_t pos = line.find('"');
        testNull(pos);
        auto [sfile, name, package] = createFile(line);
        // std::cout << "Created file " << name << endl;
        *sfile << endl;
        bool needImport = false;
        vector<string> imports;
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
                if (tempparent != package)
                {
                    imports.push_back(tempparent);
                    needImport = true;
                }
                // std::cout << "Found theParent " << parent << " " << tempparent << endl;
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
                    imports.push_back(tempinterface);
                    needImport = true;
                }
                // std::cout << "Found theInterface " << interface << endl;
            }
            getline(file, line);
            string vis;
            switch (protlvl(line))
            {
            case Public:
                vis = "public ";
                break;
            case Protected:
                vis = "protected ";
                break;
            case Private:
                vis = "private ";
                break;
            case Default:
                std::cout << "default" << endl;
                break;
            }
            if (needImport)
            {
                for (int i = 0; i < imports.size(); i++)
                {
                    *sfile << "import ";
                    *sfile << imports[i];
                    *sfile << ";\n";
                }
                *sfile << ";\n";
            }
            *sfile << vis << "class " << name << parent << interface << " {\n";
        }
        else if (line.find("theInterface") != string::npos)
        {
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
                    imports.push_back(tempparent);
                    needImport = true;
                }
                // std::cout << "Found theParent " << parent << " " << tempparent << endl;
            }
            if (needImport)
            {
                for (int i = 0; i < imports.size(); i++)
                {
                    *sfile << "import ";
                    *sfile << imports[i];
                    *sfile << ";\n";
                }
                *sfile << ";\n";
            }
            getline(file, line);
            string vis;
            switch (protlvl(line))
            {
            case Public:
                vis = "public ";
                break;
            case Protected:
                vis = "protected ";
                break;
            case Private:
                vis = "private ";
                break;
            case Default:
                std::cout << "default" << endl;
                break;
            }
            *sfile << vis << "interface " << name << parent << " {\n";
        }
        // search for it.hasMethod it.hasConstructor it.hasField
        // just seach for the string will call the function later
        while (getline(file, line))
        {
            if (line.find("it.hasMethod") != string::npos)
            {
                createMethod(&file, sfile, line);
                // std::cout << "Found method" << endl;
            }
            else if (line.find("it.hasConstructor") != string::npos)
            {
                createConstructor(&file, sfile, line, name);
                // std::cout << "Found constructor" << endl;
            }
            else if (line.find("it.hasField") != string::npos)
            {
                createVar(&file, sfile, line);
                // std::cout << "Found field" << endl;
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

    return 0;
}