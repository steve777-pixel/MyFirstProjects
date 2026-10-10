#include "validation.h"
#include "mission.h"
#include <iostream>
#include <string>

Mission::Mission(const std::string &missionId, const std::string &title, int difficulty){
    this->missionId = Validation::validateNotBlank(missionId, "Mission ID");
    this->title = Validation::validateNotBlank(title, "Mission title");
    this->difficulty = Validation::validateBetween(difficulty, 1, 10, "Mission difficulty");
    this->completed = false; // all missions are incomplete when created
}

std::string Mission::getId() const{return missionId;}
std::string Mission::getTitle() const{return title;}
int Mission::getDifficulty() const{return difficulty;}
bool Mission::isComplete() const{return completed;}

std::string Mission::getStatus() const{
    if(completed) return "Complete";
    return "Incomplete";
}

void Mission::completeMission(){
    if(completed) return;
    this->completed = true;
}

std::ostream& operator<<(std::ostream& os, const Mission& p){
    os << "Mission ID: " << p.missionId << "\nTitle: " << p.title << "\nDifficulty (1-10): " << p.difficulty << "\nStatus: " << p.getStatus();
    return os;
}
