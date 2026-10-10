#include "intelligenceReport.h"
#include "validation.h"
#include <string>
#include <compare>
#include <iostream>

IntelligenceReport::IntelligenceReport(const std::string &reportId, const std::string &description, int dangerRating){
    this->reportId = Validation::validateNotBlank(reportId, "Report ID");
    this->description = Validation::validateNotBlank(description, "Description");
    this->dangerRating = Validation::validateBetween(dangerRating, 1, 10, "Danger rating");
    this->submittedBy = "None";
}

std::string IntelligenceReport::getReportId() const{return reportId;}
std::string IntelligenceReport::getDescription() const{return description;}
int IntelligenceReport::getDangerRating() const{return dangerRating;}
std::string IntelligenceReport::getSubmitterID() const{return submittedBy;}

void IntelligenceReport::writeDescription(const std::string &description){
    this->description = Validation::validateNotBlank(description, "Description");
}
    
void IntelligenceReport::setDangerRating(int dangerRating){
    this->dangerRating = Validation::validateBetween(dangerRating, 1, 10, "Danger rating");
}

void IntelligenceReport::setSubmitterId(const std::string &id){
    this->submittedBy = Validation::validateNotBlank(id, "Submitter ID");
}


bool IntelligenceReport::operator==(const IntelligenceReport &other) const{
    return this->reportId == other.reportId;
}

std::ostream& operator<<(std::ostream &os, const IntelligenceReport &report){
    os << "ID: " << report.reportId << "\nDescription: " << report.description << "\nDanger rating (1-10): " << report.dangerRating;
    return os;
}
