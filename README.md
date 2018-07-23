# Smart XML Analyzer

A program that analyzes HTML and finds a specific element, even after changes, using a set of extracted attributes.
The program must consume the original page to collect all the required information about the target element.
Then the program should be able to find this element in diff-case HTML document that differs a bit from the original page.
Original and diff-case HTML documents should be provided to the program in each run - no persistence is required.

Consider HTML samples, as regular XML files. No image/in-browser app analysis is needed. No CSS/JS analysis is needed
(CSS/JS files are provided just for demo).

## Getting Started

java -cp <your_bundled_app>.jar <input_origin_file_path> <element_id_in_origin_file> <input_other_sample_file_path>

### Prerequisites

For running application you need java 8 and maven

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

Anastasiia Pyrlyk
