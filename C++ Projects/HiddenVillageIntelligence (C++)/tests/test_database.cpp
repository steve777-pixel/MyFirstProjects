#include "agent.h"
#include "mission.h"
#include "reconAgent.h"
#include "eliteAnbuAgent.h"
#include "infiltrationAgent.h"
#include "intelligenceReport.h"
#include <memory>
#include <cassert>
#include <iostream>

void test_recon_agent_constructor(){
    auto agent = std::make_unique<reconAgent>("AD7889012", "Naruto", 18, 2, 12);

    assert(agent->getId() == "AD7889012");
    assert(agent->getCodeName() == "Naruto");
    assert(agent->getAge() == 18);
    assert(agent->getExperience() == 2);
    assert(agent->getMissionsObserved() == 12);

    std::string expectedCSV = "RECON,Naruto,AD7889012,18,2,12";
    assert(agent->serialize() == expectedCSV);

    std::cout << "  [PASS] ReconAgent construction and initialization.\n\n";
}

void test_anbu_agent_constructor(){
    auto agent = std::make_unique<eliteAnbuAgent>("AD7889012", "Naruto", 18, 2, 12);

    assert(agent->getId() == "AD7889012");
    assert(agent->getCodeName() == "Naruto");
    assert(agent->getAge() == 18);
    assert(agent->getExperience() == 2);
    assert(agent->getClassifiedOperations() == 12);

    std::string expectedCSV = "ANBU,Naruto,AD7889012,18,2,12";
    assert(agent->serialize() == expectedCSV);

    std::cout << "  [PASS] Anbu agent construction and initialization.\n\n";
}

void test_infilt_agent_constructor(){
    auto agent = std::make_unique<infiltrationAgent>("AD7889012", "Naruto", 18, 2, 12);

    assert(agent->getId() == "AD7889012");
    assert(agent->getCodeName() == "Naruto");
    assert(agent->getAge() == 18);
    assert(agent->getExperience() == 2);
    assert(agent->getVillagesPenetrated() == 12);

    std::string expectedCSV = "INFILTRATION,Naruto,AD7889012,18,2,12";
    assert(agent->serialize() == expectedCSV);

    std::cout << "  [PASS] Infiltration agent construction and initialization.\n\n";
}

void test_mission_constructor(){
    auto mission = std::make_unique<Mission>("AD7889012", "Slay the Dragon", 10);

    assert(mission->getId() == "AD7889012");
    assert(mission->getTitle() == "Slay the Dragon");
    assert(mission->getDifficulty() == 10);
    assert(mission->getStatus() == "Incomplete");

    std::cout << "  [PASS] Mission construction and initialization.\n\n";
}

void test_intelligence_report_constructor(){
    auto report = std::make_unique<IntelligenceReport>("AX7838231", "Retirement", 1);
    report->setSubmitterId("AD7889012");

    assert(report->getReportId() == "AX7838231");
    assert(report->getDescription() == "Retirement");
    assert(report->getDangerRating() == 1);
    assert(report->getSubmitterID() == "AD7889012");

    std::cout << "  [PASS] Intelligence report construction and initialization.\n\n";
}

int main(){
    std::cout << "====================================\n";
    std::cout << " RUNNING AGENT CONSTRUCTOR UNIT TESTS\n";
    std::cout << "====================================\n\n";

    test_recon_agent_constructor();
    test_anbu_agent_constructor();
    test_infilt_agent_constructor();
    test_mission_constructor();
    test_intelligence_report_constructor();

    std::cout << "====================================\n";
    std::cout << " STATUS: ALL TESTS PASSED SUCCESSFULLY! ✅\n";
    std::cout << "====================================\n";
    return 0;
}