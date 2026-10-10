#pragma once

#include <string>
#include <compare>
#include <iostream>

class IntelligenceReport{
private:
    std::string reportId;
    std::string description;
    int dangerRating;
    std::string submittedBy;

public:
    IntelligenceReport(const std::string &reportId, const std::string &description, int dangerRating);

    std::string getReportId() const;
    std::string getDescription() const;
    int getDangerRating() const;
    std::string getSubmitterID() const;

    void writeDescription(const std::string &description);
    void setDangerRating(int dangerRating);
    void setSubmitterId(const std::string &id);

    bool operator==(const IntelligenceReport &other) const;

    friend std::ostream& operator<<(std::ostream &os, const IntelligenceReport &report);
};