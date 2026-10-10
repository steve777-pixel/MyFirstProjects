#include "agent.h"
#include "validation.h"
#include <iostream>
#include <string>
#include <vector>
#include <ranges>
#include <algorithm>
#include "exceptions.h"
#include "intelligenceDepartment.h"

Agent::Agent(const std::string &id, const std::string &codeName, int age, int yearsOfExperience){
    this->id = Validation::validateNotBlank(id, "ID");
    this->codeName = Validation::validateNotBlank(codeName, "Code name");
    Validation::validateAlessThanB(yearsOfExperience, age, "Experience years", "Age");
    this->age = Validation::validateNotNegative(age, "Age");
    this->yearsOfExperience = Validation::validateNotNegative(yearsOfExperience, "Experience years");
}

std::string Agent::getId() const {return id;}
std::string Agent::getCodeName() const {return codeName;}
int Agent::getAge() const {return age;}
int Agent::getExperience() const {return yearsOfExperience;}
std::vector<std::shared_ptr<Mission> > Agent::getMissionsList() const{
    std::vector<std::shared_ptr<Mission> > missionList;
    if(missions.empty()) return missionList;
    for(const auto &value : std::views::values(missions)){
        missionList.push_back(value);
    }
    return missionList;
}


void Agent::setCodeName(const std::string &codeName){
    this->codeName = Validation::validateNotBlank(codeName, "Code name");
}

void Agent::setAge(int age){
    Validation::validateAlessThanB(this->yearsOfExperience, age, "Experience years", "Age");
    this->age = Validation::validateNotNegative(age, "Age");
}

void Agent::setExperience(int experience){
    Validation::validateAlessThanB(experience, this->age, "Experience years", "Age");
    this->yearsOfExperience = Validation::validateNotNegative(experience, "Experience years");
}

void Agent::addExperience(int experience){
    setExperience(std::min(this->yearsOfExperience + experience, age - 1));
}

void Agent::assignMission(std::shared_ptr<Mission> mission){
    if(!mission) throw NullObjectException("Mission");
    auto [it, inserted] = missions.try_emplace(mission->getId(), mission);
    if(!inserted){
        throw DuplicateMissionException();
    }
}

void Agent::removeMission(const std::string &id){
    if(id.empty()) throw EmptyTextEception("Mission ID");
    if(missions.erase(id) == 0){
        throw MissionNotFoundException(id);
    }
}

Mission* Agent::findMissionById(const std::string &id) const{
    if(id.empty()) throw EmptyTextEception("Mission ID");
    auto it = missions.find(id);
    if(it != missions.end()){
        return it->second.get();
    }
    std::cerr << "Mission with such ID was not found\n";
    return nullptr;
}

void Agent::completeMission(const std::string &id){
    if(id.empty()) throw EmptyTextEception("Mission ID");
    Mission* mission = findMissionById(id);
    if(mission) mission->completeMission();
}

void Agent::showMissions() const{
    if(missions.empty()) return;
    for(const auto &value : std::views::values(missions)){
        std::cout << value << "\n";
    }
}

bool Agent::hasCompleted(const std::string &id) const{
    if(id.empty()) throw EmptyTextEception("Mission ID");
    Mission* mission = findMissionById(id);
    if(mission){
        return mission->isComplete();
    }
    return false;
}

void Agent::submitReport(std::unique_ptr<IntelligenceReport> report, IntelligenceDepartment *department){
    if(!department) throw NullObjectException("Department");
    if(department->findAgentById(this->id) == nullptr) throw std::out_of_range("The agent must be a part of the department to sumbit reports.");
    department->addReport(std::move(report), getId());
}

void Agent::displayInfo() const{
    std::cout << "ID: " << id << "\nName: " << codeName << "\nAge: " << age << "\nExperience: " << yearsOfExperience << " years\n";
}

bool Agent::operator==(const Agent &other) const{
    return this->id == other.id;
}
