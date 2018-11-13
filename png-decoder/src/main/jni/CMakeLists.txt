cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_CXX_FLAGS "${CMAKE_C_FLAGS} -ffunction-sections -fdata-sections -fvisibility=hidden -Wl,--gc-sections")
set(COMMON_SOURCE_DIR ${CMAKE_SOURCE_DIR}/../../../../builtin-decoder-common/)

add_library(pngdecoder SHARED
        pngdecoder.cpp
        libpng/png.c
        libpng/pngerror.c
        libpng/pngget.c
        libpng/pngmem.c
        libpng/pngpread.c
        libpng/pngread.c
        libpng/pngrio.c
        libpng/pngrtran.c
        libpng/pngrutil.c
        libpng/pngset.c
        libpng/pngtrans.c
        ${COMMON_SOURCE_DIR}/pixelcomposer.cpp
        ${COMMON_SOURCE_DIR}/sampler.cpp)

target_link_libraries(pngdecoder
        jnigraphics
        android
        z
        log)

target_include_directories(pngdecoder PRIVATE
        ${COMMON_SOURCE_DIR})
