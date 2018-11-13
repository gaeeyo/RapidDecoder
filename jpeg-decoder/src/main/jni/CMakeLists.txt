cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_CXX_FLAGS "${CMAKE_C_FLAGS} -ffunction-sections -fdata-sections -fvisibility=hidden -Wl,--gc-sections")
set(COMMON_SOURCE_DIR ${CMAKE_SOURCE_DIR}/../../../../builtin-decoder-common/)


add_library(jpegdecoder SHARED
        jpegdecoder.cpp jpgd.cpp
        ${COMMON_SOURCE_DIR}/pixelcomposer.cpp
        ${COMMON_SOURCE_DIR}/sampler.cpp)

target_link_libraries(jpegdecoder
        jnigraphics
        android
        log)

target_include_directories(jpegdecoder PRIVATE
        ${COMMON_SOURCE_DIR})
