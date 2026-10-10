#pragma once

#include "agent.h"
#include <string>

class reconAgent : public Agent{
protected:
    int missionsObserved;

public:
    reconAgent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience, int missionsObserved);

    int getMissionsObserved() const;

    void setMissionsObserved(int count);
    void addMissionCount(int count);

    void displayInfo() const override;
    double calculateThreatLevel() const override;
    std::string getType() const override;
    std::string serialize() const override;
    virtual ~reconAgent() = default;
};