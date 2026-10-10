#pragma once

#include "intelligenceReport.h"
#include "mission.h"
#include <string>
#include <vector>
#include <compare>
#include <unordered_map>

class IntelligenceDepartment;

class Agent{
protected:
    std::string id;
    std::string codeName;
    int age;
    int yearsOfExperience;
    std::unordered_map<std::string, std::shared_ptr<Mission> > missions; // shared_ptr or unique_ptr ???

public:
    Agent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience);

    std::string getId() const;
    std::string getCodeName() const;
    int getAge() const;
    int getExperience() const;
    std::vector<std::shared_ptr<Mission> > getMissionsList() const;

    void setCodeName(const std::string &codeName);
    void setAge(int age);
    void setExperience(int experience);
    void addExperience(int experience);
    void assignMission(std::shared_ptr<Mission> mission);
    void removeMission(const std::string &id);
    void completeMission(const std::string &id);
    Mission* findMissionById(const std::string &id) const;

    void showMissions() const;
    bool hasCompleted(const std::string &id) const;
    std::string getRank() const{
        int a = calculateThreatLevel();
        if(a <= 25) return "D";
        if(a <= 50) return "C";
        if(a <= 100) return "B";
        if(a <= 200) return "A";
        return "S";
    }

    void submitReport(std::unique_ptr<IntelligenceReport> report, IntelligenceDepartment *department);

    virtual void displayInfo() const;
    virtual double calculateThreatLevel() const = 0;
    virtual std::string getType() const = 0;
    virtual std::string serialize() const = 0;
    virtual ~Agent() = default;

    // auto operator<=>(const Agent& other) const = default;

    bool operator==(const Agent &other) const;
};