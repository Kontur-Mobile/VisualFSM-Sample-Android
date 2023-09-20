import argparse
import os
import re
import sys

parser = argparse.ArgumentParser(description='Generate FSM graph with coverage')
parser.add_argument("-all", '--all_transitions_file_path', type=str,
                    help='path to file with base state and all transitions of FSM', default="")
parser.add_argument("-covered", '--covered_transitions_file_path', type=str,
                    help='path to file with transitions covers by tests', default="")
parser.add_argument("-input", '--input_path', type=str, help='path to package with all and covered transitions info',
                    default="./")
parser.add_argument("-output", '--output_path', type=str, help='path to results output', default="./")
args = parser.parse_args()


def write_coverage_graph(all_transitions_file_path: str, covered_transitions_file_path: str, results_output_path):
    # absolute dir the script is in
    script_dir = os.path.dirname(__file__)

    all_transitions_file_lines = list()
    covered_transitions_file_lines = list()

    if all_transitions_file_path != "":
        with open(os.path.join(script_dir, all_transitions_file_path)) as file:
            all_transitions_file_lines = file.read().splitlines()

    if covered_transitions_file_path != "":
        with open(os.path.join(script_dir, covered_transitions_file_path)) as file:
            covered_transitions_file_lines = file.read().splitlines()

    all_transitions_info = set()
    covered_transitions_info = set()
    base_state = "undefinedBaseState"
    initial_state = "undefinedInitialState"

    for index, allTransitionsFileLine in enumerate(all_transitions_file_lines):
        if index == 0:
            base_state = allTransitionsFileLine[:-2]
        elif index == 1:
            initial_state = allTransitionsFileLine[:-2]
        else:
            split = allTransitionsFileLine.split(",")
            all_transitions_info.add((split[0], split[1], split[2]))

    for coveredTransitionsFileLine in covered_transitions_file_lines:
        split = coveredTransitionsFileLine.split(",")
        covered_transitions_info.add((split[0], split[1], split[2]))

    all_states_info = set()
    covered_states_info = set()

    for index, allTransitionsFileLine in enumerate(all_transitions_file_lines):
        if index == 0 or index == 1:
            continue
        split = allTransitionsFileLine.split(",")
        all_states_info.add(split[1])
        all_states_info.add(split[2])

    for coveredTransitionsFileLine in covered_transitions_file_lines:
        split = coveredTransitionsFileLine.split(",")
        covered_states_info.add(split[1])
        covered_states_info.add(split[2])

    if results_output_path != "":
        if not os.path.exists(results_output_path):
            os.makedirs(results_output_path)

    with open(results_output_path + base_state + "CoverageGraph.dot", "w") as file:
        file.write("digraph " + base_state + "Transitions {\n")
        file.write("\"" + initial_state + "\"\n")
        for state in all_states_info:
            if state in covered_states_info:
                continue
            file.write("\"" + state + "\" [label=<<font color=\"red\">")
            file.write(state)
            file.write("</font>>")
            file.write(" color=\"red\"]\n")

        for info in all_transitions_info:
            file.write("\"" + info[1] + "\"")
            file.write(" -> ")
            file.write("\"" + info[2] + "\"")
            file.write("[label=<<font color=\"")
            if info not in covered_transitions_info:
                file.write("red")
            else:
                file.write("black")
            file.write("\"> " + info[0] + "</font>>")
            if info not in covered_transitions_info:
                file.write(" color=\"red\"")
            file.write("]\n")
        file.write("}")


input_path: str
output_path: str

if args.input_path[-1] == "/":
    input_path = args.input_path
else:
    input_path = args.input_path + "/"

if args.output_path[-1] == "/":
    output_path = args.output_path
else:
    output_path = args.output_path + "/"

if args.all_transitions_file_path != "" and args.covered_transitions_file_path != "":
    write_coverage_graph(args.all_transitions_file_path, args.covered_transitions_file_path, output_path)
    sys.exit()

file_names = [f for f in os.listdir(input_path) if os.path.isfile(os.path.join(input_path, f))]

all_transitions_file_pattern = re.compile(".*AllTransitions.csv$")

for file_name in file_names:
    if all_transitions_file_pattern.fullmatch(file_name):
        all_transitions_file_end_length = len("AllTransitions.csv")
        base_state_name = file_name[:-all_transitions_file_end_length]
        covered_transitions_file_name = base_state_name + "CoveredTransitions.csv"
        if covered_transitions_file_name in file_names:
            write_coverage_graph(input_path + file_name, input_path + covered_transitions_file_name, output_path)
        else:
            write_coverage_graph(input_path + file_name, "", output_path)
