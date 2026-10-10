#pragma once

#include "intelligenceReport.h"
#include "agent.h"
#include <tuple>
#include <string>
#include <compare>
#include <vector>
#include <unordered_map>

class IntelligenceDepartment{
private:
    std::string id;
    std::unordered_map<std::string, std::unique_ptr<Agent> > agents;
    std::unordered_map<std::string, std::unique_ptr<IntelligenceReport> > reports;

    std::vector<Agent*> getRawAgentPointers() const;

public:
    IntelligenceDepartment(const std::string &id);

    std::string getId() const;

    const std::unordered_map<std::string, std::unique_ptr<Agent>>& getAgents() const;

    void addAgent(std::unique_ptr<Agent> agent);

    void removeAgent(const std::string &id);

    Agent* findAgentById(const std::string &id) const;

    void displayAllAgents() const;

    void addReport(std::unique_ptr<IntelligenceReport> report, const std::string &submitterId);

    void removeReport(const std::string &id);

    IntelligenceReport* findReportById(const std::string &id) const;

    void displayAllReports() const;

    double getTotalThreatLevel() const;

    Agent* getMostDangerousAgent() const;

    std::vector<Agent*> sortAgentsByThreatLevel() const;

    std::vector<Agent*> sortAgentsByAge() const;

    std::vector<Agent*> sortAgentsByExperience() const;

    std::vector<Agent*> getAgentsByType(const std::string &type) const;

    double getAverageThreatLevel() const;

    double getAverageExperience() const;

    double getAverageAge() const;

    std::tuple<int, int, int> getAgentCountByType() const;

    bool operator==(const IntelligenceDepartment &other) const;
};