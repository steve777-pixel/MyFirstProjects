#pragma once

#include <stdexcept>
#include <string>

class EmptyTextEception : public std::runtime_error{
public:
    explicit EmptyTextEception(const std::string &value)
        : std::runtime_error("Error: " + value + " cannot be empty.") {}
};

class NullObjectException : public std::runtime_error{
public:  
    explicit NullObjectException(const std::string &value)
        : std::runtime_error("Error:" + value + " cannot be null.") {}
};

class AgentNotFoundException : public std::runtime_error{
public:
    explicit AgentNotFoundException(const std::string &id)
        : std::runtime_error("Error: Agent with ID " + id + " not found.") {}
};

class ReportNotFoundException : public std::runtime_error{
public:
    explicit ReportNotFoundException(const std::string &id)
        : std::runtime_error("Error: Report with ID " + id + " not found.") {}
};

class MissionNotFoundException : public std::runtime_error{
public:
    explicit MissionNotFoundException(const std::string &id)
        : std::runtime_error("Error: Mission with ID " + id + " not assigned to this agent.") {}
};

class DuplicateAgentException : public std::runtime_error{
public:
    explicit DuplicateAgentException()
        : std::runtime_error("Error: Agent is already in the department.") {}
};

class DuplicateReportException : public std::runtime_error{
public:
    explicit DuplicateReportException()
        : std::runtime_error("Error: Report is already in the department.") {}
};

class DuplicateMissionException : public std::runtime_error{
public:
    explicit DuplicateMissionException()
        : std::runtime_error("Error: Mission is already in the assigned to this agent.") {}
};
