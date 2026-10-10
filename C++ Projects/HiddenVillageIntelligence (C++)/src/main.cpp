#include <iostream>
#include <vector>
#include "agent.h"
#include "mission.h"
#include "reconAgent.h"
#include "intelligenceDatabase.h"
#include "intelligenceDepartment.h"

using std::cin; using std::cout;

int main(){
    // cout << "Hello!\n";

    // reconAgent agent("AD7889012", "Naruto", 18, 2, 12);
    // reconAgent agent1("AD7889012", "Naruto", 18, 2, 12);
    // auto agent = std::make_unique<reconAgent>("AD7889012", "Naruto", 18, 2, 12);
    // auto agent1 = std::make_unique<reconAgent>("AD7889011", "Sakura", 18, 1, 10);
    // Agent* spy = new reconAgent("AX9832931", "Sasuke", 47, 17, 125); Not suggested!
    // agent.displayInfo();
    // cout << ((*agent1 == *agent) ? "YES\n" : "NO\n");

    // std::vector<std::unique_ptr<Agent> > agents;
    // agents.push_back(std::move(agent));
    // agents.push_back(std::move(agent1));
    
    // auto department1 = std::make_unique<IntelligenceDepartment>("XH8900118");
    // cout << agent->getRank() << "\n";
    // department1->addAgent(std::move(agent));
    // department1->addAgent(std::move(agent1));
    // department1->displayAllAgents();

    // IntelligenceDatabase::SaveAllAgentsToTextFile(*department1, "../data/agents.txt");

    // Agent* mostPowerful = department1->getMostDangerousAgent();
    // mostPowerful->displayInfo();
    // department1->addAgent(std::move(agent1));

    // Mission mission("IJ8709123", "Kill the dragon", 12);
    // cout << mission << "\n";

    auto department = std::make_unique<IntelligenceDepartment>("IO8910234");
    auto agent = std::make_unique<reconAgent>("AD7889012", "Naruto", 18, 2, 12);
    auto agent1 = std::make_unique<reconAgent>("AD7889013", "Sasuke", 19, 2, 14);
    auto agent2 = std::make_unique<reconAgent>("AD7889014", "Sakura", 16, 1, 8);

    department->addAgent(std::move(agent));
    department->addAgent(std::move(agent1));
    department->addAgent(std::move(agent2));

    cout << department->getAverageThreatLevel() << "\n";
    cout << department->getAverageExperience() << "\n";
    cout << department->getAverageAge() << "\n";

    return 0;
}