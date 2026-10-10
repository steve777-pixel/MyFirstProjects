#include "core_algorithms.h"

#include <iostream>
#include <vector>
#include <cmath>
#include <numeric>

double calculatePopulationVariance(const std::vector<double> &arr){
    if(arr.empty()) return 0.0;

    double sum = std::accumulate(arr.begin(), arr.end(), 0.0);
    double mean = sum / arr.size();

    double squared_difference_sum = 0.0;
    for(double value : arr){
        squared_difference_sum += pow(value - mean, 2);
    }
    return squared_difference_sum / arr.size();
}

double calculatePopulationSD(const std::vector<double> &arr){
    return  sqrt(calculatePopulationVariance(arr));
}