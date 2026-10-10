#include "intelligenceDepartment.h"
#include "intelligenceReport.h"
#include "reconAgent.h"
#include "agent.h"
#include <cassert>
#include <iostream>
#include <memory>
#include <cmath>

void test_intelligence_department_constructor(){
    auto department = std::make_unique<IntelligenceDepartment>("IO8910234");

    assert(department->getId() == "IO8910234");

    std::cout << "  [PASS] Intelligence department construction and initialization.\n\n";
}

void test_department_agents(){
    auto department = std::make_unique<IntelligenceDepartment>("IO8910234");
    auto agent = std::make_unique<reconAgent>("AD7889012", "Naruto", 18, 2, 12);
    auto agent1 = std::make_unique<reconAgent>("AD7889013", "Sasuke", 19, 2, 14);
    auto agent2 = std::make_unique<reconAgent>("AD7889014", "Sakura", 16, 1, 8);

    department->addAgent(std::move(agent));
    department->addAgent(std::move(agent1));
    department->addAgent(std::move(agent2));

    Agent* testAgent = department->getMostDangerousAgent();

    assert(testAgent->getCodeName() == "Sasuke");
    assert(department->getTotalThreatLevel() == 51);
    assert(department->getAverageThreatLevel() == 17.0);
    const double EPSILON = 0.0001;
    assert(std::abs(department->getAverageExperience() - 1.66667) < EPSILON);
    assert(std::abs(department->getAverageAge() - 17.6667) < EPSILON);

    std::cout << "  [PASS] Intelligence department actions with agents.\n\n";
}

void test_department_reports(){
    auto department = std::make_unique<IntelligenceDepartment>("IO8910234");
    auto report = std::make_unique<IntelligenceReport>("AX7838231", "Retirement", 1);
    auto agent = std::make_unique<reconAgent>("AD7889012", "Naruto", 18, 2, 12);
    department->addReport(std::move(report), agent->getId());

    IntelligenceReport* testReport = department->findReportById("AX7838231");

    assert(testReport->getReportId() == "AX7838231");
    assert(testReport->getDescription() == "Retirement");
    assert(testReport->getDangerRating() == 1);
    assert(testReport->getSubmitterID() == "AD7889012");

    std::cout << "  [PASS] Intelligence department actions with reports.\n\n";
}

int main(){
    std::cout << "====================================\n";
    std::cout << " RUNNING DEPARTMENT UNIT TESTS\n";
    std::cout << "====================================\n\n";

    test_intelligence_department_constructor();
    test_department_agents();
    test_department_reports();
    
    std::cout << "====================================\n";
    std::cout << " DEPARTMENT STATUS: ALL PASSED ✅\n";
    std::cout << "====================================\n";
    return 0;
}