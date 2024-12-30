# ImageFinder

## Overview
ImageFinder is a Java-based web crawler designed for the Eulerity Hackathon Challenge. The application crawls a user-provided URL to extract all image URLs from the webpage and its sub-pages within the same domain. The project utilizes multi-threading for efficient crawling and ensures that previously visited pages are not re-crawled. It also offers additional functionality, such as the ability to detect potential logos and a front-end for user interaction.

## Features
### Core Functionality
- **Web Crawling**: Extracts image URLs from a webpage using libraries such as `Jsoup`.
- **Sub-page Crawling**: Crawls sub-pages of the given domain to gather additional images.
- **Multi-threading**: Implements multi-threading to crawl multiple sub-pages simultaneously for enhanced performance.
- **Domain Restriction**: Ensures the crawler stays within the same domain as the input URL.
- **Avoid Duplicate Crawls**: Prevents re-crawling of already visited pages.

### Extra Functionality
- **Friendly Crawling**: Implements mechanisms to reduce the risk of being flagged or banned by websites.
- **Logo Detection**: Attempts to identify images that may represent logos.
- **Interactive Front-end**: Provides a user-friendly interface built with HTML, CSS, and JavaScript for entering URLs and displaying results.

## Technologies Used
- **Java 8**: Core programming language.
- **Maven**: For project management and build automation.
- **Jsoup**: Library for HTML parsing and web scraping.
- **Jetty Server**: Lightweight web server for hosting the application.
- **HTML/CSS/JavaScript**: For building the front-end interface.
- **Multi-threading**: For efficient and concurrent crawling.

## Setup and Installation
### Prerequisites
- **Java 8**: Exact version is required (Java 9+ is not supported).
- **Maven 3.5 or higher**: For building and running the project.

### Build Instructions
1. Clone this repository:
   ```bash
   git clone git@github.com:santosh-jorka/Image_Web_crawlerr.git
   cd imagefinder
