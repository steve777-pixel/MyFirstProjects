#pragma once

#include <string>
#include <stdexcept>
#include <type_traits>

namespace Validation{

    inline std::string validateNotBlank(const std::string &value, const std::string &fieldName){
        if(value.empty()){
            throw std::invalid_argument(fieldName + " cannot be empty.");
        }
        return value;
    }

    template <typename T>
    inline T validateNotNegative(T value, const std::string &fieldName){
        static_assert(std::is_arithmetic_v<T>, "Template parameter must be numeric type.");
        if(value < 0){
            throw std::invalid_argument(fieldName + " cannot be negative.");
        }
        return value;
    }

    template <typename T>
    inline void validateAlessThanB(T lesserValue, T greaterValue, const std::string &firstName, const std::string &secondName){
        static_assert(std::is_arithmetic_v<T>, "Template parameters must be numeric type.");
        if(lesserValue >= greaterValue){
            throw std::invalid_argument(firstName + " must be less than " + secondName + ".");
        }
    }

    template <typename A, typename U>
    inline A validateBetween(A value, U minValue, U maxValue, const std::string &fieldName){
        static_assert(std::is_arithmetic_v<A>, "Template parameters must be numeric type.");
        static_assert(std::is_arithmetic_v<U>, "Template parameters must be numeric type.");
        if(value < minValue || value > maxValue){
            throw std::invalid_argument(fieldName + " should be in range [" + std::to_string(minValue) + "-" + std::to_string(maxValue) + "].");
        }
        return value;
    }
}