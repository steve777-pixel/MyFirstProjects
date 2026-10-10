cmake_minimum_required(VERSION 3.15)
project(IntelligenceSystem CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

include_directories(include)

# 1. Collect all your actual source files
file(GLOB SOURCES "src/*.cpp")

# 2. Production Build: Create the primary app target
# This assumes src/main.cpp is inside your src/ folder
add_executable(app ${SOURCES})

# 3. Testing Build: Create a second, standalone executable runner
# We copy all the domain behavior but replace src/main.cpp with your test file
set(TEST_SOURCES ${SOURCES})
list(REMOVE_ITEM TEST_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/src/main.cpp")

add_executable(run_tests tests/test_database.cpp ${TEST_SOURCES})

add_executable(run_department_tests tests/test_department.cpp ${TEST_SOURCES})