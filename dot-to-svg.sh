#!/bin/bash

#A script for generating graphs in svg format, based on their representation in dot format.

if [[ ! -n "$1" ]]; then
echo "Use with path to directory: ./dot-to-svg.sh \"./path-to-dots\""
echo "Script depends on dot command from graphviz tool (apt install graphviz)"
exit 1
fi

mkdir "$1"/svg

for filename in "$1"/*.dot; do
    dot -Tsvg "$filename" > "$1/svg/$(basename $filename .dot).svg"
done