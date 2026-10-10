#pragma once

#include "intelligenceDepartment.h"
#include <iostream>
#include <fstream>
#include <string>

class IntelligenceDatabase{
public:
    static void SaveAllAgentsToTextFile(const IntelligenceDepartment& department, const std::string &fileName){
        std::ofstream outFile(fileName);

        if(!outFile){
            std::cerr << "Error: Could not open file " << fileName << " for writing.\n";
            return;
        }

        for(const auto& [id, agent] : department.getAgents()){
            if(agent){
                outFile << agent->serialize() << "\n";
            }
        }

        outFile.close();
    }
};