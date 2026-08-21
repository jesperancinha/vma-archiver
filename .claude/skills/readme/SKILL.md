---
name: Readme standards
description: Conventions to use in Readme files
---

## 1. Remove the usage of unresolved anchors

Unresolved anchors that don't translate to a link or a website should be removed.
The most important examples of this are the usage of `(#)`. This used to be a guarantee that the image would be displayed.
This is no longer necessary, and the svg icons get generated with or without the link.


### Example 1

Replace

```text
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/your-finance-je.svg)](#)
```

with 

```text
![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/your-finance-je.svg)
```

## 2. Don't use obsolete HTML properties for showing images with divs

1. Replace usages of `<div align="center">` with `<div style="text-align: center;">`
2. If the `alt` attribute is missing from the `img` node, please add it with the text of the `a` (anchor), property value of its `title` property.

### Example 1

Replace

```html
<div align="center">
      <a title="Learning JWT security using KumuluzEE — The finances of a league of the environment" href="https://itnext.io/learning-jwt-security-using-kumuluzee-the-finances-of-a-league-of-the-environment-2f541e99cc90">
     <img 
          src="./docs/images/articles.your.finance.intro.png" 
          style="width:100%;">
      </a>
</div>
```

with

```html
<div style="text-align: center;">
      <a title="Learning JWT security using KumuluzEE — The finances of a league of the environment" href="https://itnext.io/learning-jwt-security-using-kumuluzee-the-finances-of-a-league-of-the-environment-2f541e99cc90">
     <img 
          src="./docs/images/articles.your.finance.intro.png" 
          style="width:100%;" alt="Learning JWT security using KumuluzEE — The finances of a league of the environment">
      </a>
</div>

```

## 3. Remove obsolete badges

Some badges are no longer usable and don't work because some of the frameworks they used have been discontinued.
For all `.md` files, or other markdown files, please remove badges from the following list:

1. [![codebeat badge]()]() - All codebeat badges should be removed, along with any references to using it.
2. [![BCH compliance]()]() - All BCH badges should be removed, along with any references to using it.

## 4. `.md` files in Submodules, or subfolders, should not contain any of the following badges

The following badges are applied to the whole project, hence why it makes no sense to keep them in submodules or subfolders.
These badges should only be part of root `Readme.md` files. They should also be removed from other .md files if found.

1. ![Twitter URL]()]()
2. ![Generic badge]()]()
3. ![GitHub release]()]()
4. ![GitHub License]()]()
5. ![CircleCI]()]()
6. ![Build status]()]()
7. ![jeorg-spring-master-test-drives](https://github.com/jesperancinha/jeorg-spring-master-test-drives/actions/workflows/jeorg-spring-master-test-drives.yml/badge.svg)](https://github.com/jesperancinha/jeorg-spring-master-test-drives/actions/workflows/jeorg-spring-master-test-drives.yml)
8. ![Codacy Badge]()]()
9. ![codebeat badge]()]()
10. ![BCH compliance]()]()
11. ![Known Vulnerabilities]()]()
12. ![Codacy Badge]()]()
13. ![Coverage Status]()]()
14. ![codecov]()]()
15. ![GitHub language count]()]()
16. ![GitHub top language]()]()
17. ![GitHub top language]()]()

The pipeline GitHub action badges usually have the name of the project. They usually have `actions/workflows` in the links.
These should only be part of root `Readme.md` files. They should also be removed from other .md files if found.

## 5. Please remove emojis from badges

There may be other badges with emojis in their presentation text.
Most of these bages are the Generic one:

[![Generic badge]()]()

Look for these badges and simply remove the emoji from the presentation text, if any is found.


## 6. Standard baseline for Readme.md parent files

All projects under `jesperancinha` and under `jesperancinhaorg` should contain a Readme.md file.
This file should run the following principles as baseline:

- There should existe ate least one Readme.md at the root of the project.
- Parent Readme.md files should contain at least the following badges as in the following example

```markdown
# PROJECT NAME

---


[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Buy%20Odd%20Yucca%20Concert&color=informational)](https://github.com/jesperancinha/buy-odd-yucca-concert)

[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![Build, Test, Coverage and Report](https://github.com/jesperancinha/buy-odd-yucca-concert/actions/workflows/buy-odd-yucca-concert.yml/badge.svg)](https://github.com/jesperancinha/buy-odd-yucca-concert/actions/workflows/buy-odd-yucca-concert.yml)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/buy-odd-yucca-concert.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/buy-odd-yucca-concert.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/buy-odd-yucca-concert.svg)](#)

[![Project Skills](https://github.com/jesperancinha/project-skills/raw/master/badges/skills-badge.svg)](TechStack.md)
---

```

The example file is taken from bject `buy-odd-yucca-concert`. The analysis should check if any badges are missing, sometimes we do miss all of them, and in both cases, add all of the missing badges with the format and order given above. Never remove existing badges.
