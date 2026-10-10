#pragma once

#include "agent.h"
#include <string>

class infiltrationAgent : public Agent{
protected:
    int villagesPenetrated;

public:
    infiltrationAgent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience, int villagesPenetrated);

    int getVillagesPenetrated() const;

    void setVillagesPenetrated(int amount);
    void addVillagesPenetrated(int amount);

    void displayInfo() const override;
    double calculateThreatLevel() const override;
    std::string getType() const override;
    std::string serialize() const override;
    virtual ~infiltrationAgent() = default;
};