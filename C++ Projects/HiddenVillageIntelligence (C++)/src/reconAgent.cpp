#include "intelligenceDepartment.h"
#include "reconAgent.h"
#include "validation.h"
#include <iostream>

reconAgent::reconAgent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience, int missionsObserved)
    : Agent(id, codeName, age, yearsOfExperience)
{
    this->missionsObserved = Validation::validateNotNegative(missionsObserved, "Observed missions count");
}

int reconAgent::getMissionsObserved() const {return missionsObserved;}

void reconAgent::setMissionsObserved(int count){
    this->missionsObserved = Validation::validateNotNegative(count, "Mission count");
}
void reconAgent::addMissionCount(int count){
    this->missionsObserved += Validation::validateNotNegative(count, "Mission count");
}

void reconAgent::displayInfo() const{
    Agent::displayInfo();

    std::cout << "Missions observed: " << missionsObserved << "\n";
}

std::string reconAgent::getType() const {return "Recon Agent";}

std::string reconAgent::serialize() const{
    return "RECON," + codeName + "," + id + "," + std::to_string(age) + "," + std::to_string(yearsOfExperience) + "," + std::to_string(missionsObserved);
}

double reconAgent::calculateThreatLevel() const{return missionsObserved * 1.5;}