#!/bin/bash
for i in *.shp
do
    #ogr2ogr -t_srs EPSG:4326 $i $i
    #ogr2ogr -s_srs EPSG:4326 -t_srs "+proj=longlat +ellps=WGS84 +pm=180 +datum=WGS84 +no_defs" $i $i
    #For Fiji (FMG)
    ogr2ogr --config CENTER_LONG 180 -t_srs EPSG:4326 -t_srs EPSG:4326 $i $i
done 
