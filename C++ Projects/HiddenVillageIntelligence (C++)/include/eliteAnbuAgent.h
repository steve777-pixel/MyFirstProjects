#pragma once

#include "agent.h"

class eliteAnbuAgent : public Agent{
protected:
    int classifiedOperations;
public:
    eliteAnbuAgent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience, int classifiedOperations);

    int getClassifiedOperations() const;

    void setClassifiedOperations(int amount);
    void addClassifiedOperations(int amount);

    void displayInfo() const override;
    double calculateThreatLevel() const override;
    std::string getType() const override;
    std::string serialize() const override;
    virtual ~eliteAnbuAgent() = default;
};