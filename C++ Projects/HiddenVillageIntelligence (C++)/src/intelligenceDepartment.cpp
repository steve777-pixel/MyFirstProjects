#include "intelligenceDepartment.h"
#include "infiltrationAgent.h"
#include "eliteAnbuAgent.h"
#include "exceptions.h"
#include "validation.h"
#include "reconAgent.h"
#include <unordered_map>
#include <algorithm>
#include <stdexcept>
#include <iostream>
#include <ranges>
#include <vector>
#include "agent.h"
#include <tuple>

IntelligenceDepartment::IntelligenceDepartment(const std::string &id){
    this->id = Validation::validateNotBlank(id, "Department ID");
}

std::string IntelligenceDepartment::getId() const{return id;}

const std::unordered_map<std::string, std::unique_ptr<Agent>>& IntelligenceDepartment::getAgents() const {return agents;}

void IntelligenceDepartment::addAgent(std::unique_ptr<Agent> agent){
    if(agent == nullptr){
        throw NullObjectException("Agent");
    }
    auto [it, inserted] = agents.try_emplace(agent->getId(), std::move(agent));
    if(!inserted){
        throw DuplicateAgentException();
    }
}

void IntelligenceDepartment::removeAgent(const std::string &id){
    if(id.empty()){
        throw EmptyTextEception(" Agent ID");
    }
    if(agents.erase(id) == 0){
        throw AgentNotFoundException(id);
    }
}

Agent* IntelligenceDepartment::findAgentById(const std::string &id) const{
    if(id.empty()){
        throw EmptyTextEception("Agent ID");
    }
    auto it = agents.find(id);
    if(it != agents.end()){
        return it->second.get();
    }
    std::cerr << "Agent with such ID was not found.\n";
    return nullptr;
}

void IntelligenceDepartment::displayAllAgents() const{
    if(agents.empty()) return;
    std::cout << "\nAll " << agents.size() << " agents of the department!\n\n";
    for(const auto &value : std::views::values(agents)){
        value->displayInfo();
        std::cout << "\n";
    }
}

void IntelligenceDepartment::addReport(std::unique_ptr<IntelligenceReport> report, const std::string &submitterId){
    if(!report) throw NullObjectException("Report");
    if(reports.count(report->getReportId()) > 0){
        throw DuplicateReportException();        
    }
    report->setSubmitterId(submitterId);
    reports.emplace(report->getReportId(), std::move(report));
}

void IntelligenceDepartment::removeReport(const std::string &id){
    if(id.empty()) throw EmptyTextEception("Report ID");
    if(reports.erase(id) == 0){
        throw ReportNotFoundException(id);
    }
}

IntelligenceReport* IntelligenceDepartment::findReportById(const std::string &id) const{
    if(reports.empty()) return nullptr;
    auto it = reports.find(id);
    if(it != reports.end()){
        return it->second.get();
    }
    std::cerr << "Report with such ID was not found.\n";
    return nullptr;
}

void IntelligenceDepartment::displayAllReports() const{
    if(reports.empty()) return;
    std::cout << "All " << reports.size() << " reports:\n\n";
    for(const auto &value : std::views::values(reports)){
        std::cout << value << "\n";
    }
    std::cout << "\n";
}

double IntelligenceDepartment::getTotalThreatLevel() const{
    double total = 0.0; 
    for(const auto &value : std::views::values(agents)){
        total += value->calculateThreatLevel(); 
    }
    return total;
}

Agent* IntelligenceDepartment::getMostDangerousAgent() const{
    if(agents.empty()){
        return nullptr;
    }
    Agent* curr = nullptr;
    double maxThreat = -1.0;
    for(const auto &value : std::views::values(agents)){
        double currentThreat = value->calculateThreatLevel();
        if(currentThreat > maxThreat){
            maxThreat = currentThreat;
            curr = value.get();
        }
    }
    return curr;
}

std::vector<Agent*> IntelligenceDepartment::getRawAgentPointers() const{
    std::vector<Agent*> ptrs;
    ptrs.reserve(agents.size());
    for(const auto &value : std::views::values(agents)){
        ptrs.push_back(value.get());
    }
    return ptrs;
}

std::vector<Agent*> IntelligenceDepartment::sortAgentsByThreatLevel() const{
    auto sortedAgents = getRawAgentPointers();
    std::sort(sortedAgents.begin(), sortedAgents.end(), [](Agent* a, Agent* b){
        return a->calculateThreatLevel() > b->calculateThreatLevel();
    });
    return sortedAgents;
}

std::vector<Agent*> IntelligenceDepartment::sortAgentsByAge() const{
    auto sortedAgents = getRawAgentPointers();
    std::sort(sortedAgents.begin(), sortedAgents.end(), [](Agent* a, Agent* b){
        return a->getAge() > b->getAge();
    });
    return sortedAgents;
}

std::vector<Agent*> IntelligenceDepartment::sortAgentsByExperience() const{
    auto sortedAgents = getRawAgentPointers();
    std::sort(sortedAgents.begin(), sortedAgents.end(), [](Agent* a, Agent* b){
        return a->getExperience() > b->getExperience();
    });
    return sortedAgents;
}

std::vector<Agent*> IntelligenceDepartment::getAgentsByType(const std::string &type) const{
    if(type.empty() && type != "Elite Anbu Agent" && type != "Infiltration Agent" && type != "Recon Agent"){
        throw std::invalid_argument("Please, enter a valid agent type:\n1. Elite Anbu Agent\n2. Infiltration Agent\n3. Recon Agent");
    }
    std::vector<Agent*> anbus;
    if(agents.empty()) return anbus;
    for(const auto &value : std::views::values(agents)){
        if(value->getType() == type){
            anbus.push_back(value.get());
        }
    }
    return anbus;
}

double IntelligenceDepartment::getAverageThreatLevel() const{
    if(agents.empty()) return 0.0;
    double sum = 0.0;
    for(const auto& agent : std::views::values(agents)){
        sum += static_cast<double>(agent->calculateThreatLevel());
    }
    return sum / agents.size();
}

double IntelligenceDepartment::getAverageExperience() const{
    if(agents.empty()) return 0.0;
    double sum = 0.0;
    for(const auto& agent : std::views::values(agents)){
        sum += static_cast<double>(agent->getExperience());
    }
    return sum / agents.size();
}

double IntelligenceDepartment::getAverageAge() const{
    if(agents.empty()) return 0.0;
    double sum = 0.0;
    for(const auto& agent : std::views::values(agents)){
        sum += static_cast<double>(agent->getAge());
    }
    return sum / agents.size();
}

std::tuple<int, int, int> IntelligenceDepartment::getAgentCountByType() const{
    if(agents.empty()) return {0,0,0};
    int reconCount = 0, anbuCount = 0, infiltCount = 0;
    for(const auto& agent : std::views::values(agents)){
        if(!agent) continue;
        if(dynamic_cast<reconAgent*>(agent.get())){
            reconCount++;
        }else if (dynamic_cast<eliteAnbuAgent*>(agent.get())) { // Adjust class name to match yours
            anbuCount++;
        } else if (dynamic_cast<infiltrationAgent*>(agent.get())) {
            infiltCount++;
        }
    }
    return {reconCount, anbuCount, infiltCount};
}

bool IntelligenceDepartment::operator==(const IntelligenceDepartment &other) const{
    return this->id == other.id;
}