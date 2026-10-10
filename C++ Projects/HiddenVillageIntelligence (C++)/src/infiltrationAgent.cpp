#include "intelligenceDepartment.h"
#include "infiltrationAgent.h"
#include "validation.h"
#include <iostream>

infiltrationAgent::infiltrationAgent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience, int villagesPenetrated)
    : Agent(id, codeName, age, yearsOfExperience)
{
    this->villagesPenetrated = Validation::validateNotNegative(villagesPenetrated, "Villages penetrated");
}

int infiltrationAgent::getVillagesPenetrated() const{return villagesPenetrated;}

void infiltrationAgent::setVillagesPenetrated(int amount){
    this->villagesPenetrated = Validation::validateNotNegative(amount, "Villages penetrated");
}

void infiltrationAgent::addVillagesPenetrated(int amount){
    this->villagesPenetrated += Validation::validateNotNegative(amount, "Villages penetrated");
}

void infiltrationAgent::displayInfo() const{
    Agent::displayInfo();

    std::cout << "Villages penetrated: " << villagesPenetrated << "\n";
}

std::string infiltrationAgent::getType() const{return "Infiltration Agent";}

std::string infiltrationAgent::serialize() const{
    return "INFILTRATION," + codeName + "," + id + "," + std::to_string(age) + "," + std::to_string(yearsOfExperience) + "," + std::to_string(villagesPenetrated);
}

double infiltrationAgent::calculateThreatLevel() const{ return villagesPenetrated * 3.0;}