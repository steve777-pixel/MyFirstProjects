#pragma once

#include <string>

class Mission{
private:
    std::string missionId;
    std::string title;
    int difficulty; // 1-10
    bool completed;

public:
    Mission(const std::string &missionId, const std::string &title, int difficulty);

    std::string getId() const;
    std::string getTitle() const;
    int getDifficulty() const;
    bool isComplete() const;
    std::string getStatus() const;

    void completeMission();

    friend std::ostream& operator<<(std::ostream& os, const Mission& p);
};