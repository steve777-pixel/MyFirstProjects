#include "intelligenceDepartment.h"
#include "eliteAnbuAgent.h"
#include "validation.h"
#include <iostream>

eliteAnbuAgent::eliteAnbuAgent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience, int classifiedOperations)
: Agent(id, codeName, age, yearsOfExperience)
{
    this->classifiedOperations = Validation::validateNotNegative(classifiedOperations, "Classified operations");
}

int eliteAnbuAgent::getClassifiedOperations() const{return classifiedOperations;}

void eliteAnbuAgent::setClassifiedOperations(int amount){
    this->classifiedOperations = Validation::validateNotNegative(amount, "Classified operations");
}
    
void eliteAnbuAgent::addClassifiedOperations(int amount){
    this->classifiedOperations += Validation::validateNotNegative(amount, "Classified operations");
}

void eliteAnbuAgent::displayInfo() const{
    Agent::displayInfo();

    std::cout << "Classified operations: " << classifiedOperations << "\n";
}

std::string eliteAnbuAgent::getType() const{return "Elite Anbu Agent";}

std::string eliteAnbuAgent::serialize() const{
    return "ANBU," + codeName + "," + id + "," + std::to_string(age) + "," + std::to_string(yearsOfExperience) + "," + std::to_string(classifiedOperations);
}

double eliteAnbuAgent::calculateThreatLevel() const{return classifiedOperations * 5.0;}