# vma-archiver

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=%20Checkout%20this%20%40github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/vma-archiver)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=VMA%20Archiver%20📼%20&color=informational)](https://github.com/jesperancinha/vma-archiver)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/vma-archiver.svg)](#)

[![CircleCI](https://circleci.com/gh/jesperancinha/vma-archiver.svg?style=svg)](https://circleci.com/gh/jesperancinha/vma-archiver)
[![Build status](https://ci.appveyor.com/api/projects/status/x2q0u359vwgl6rrl?svg=true)](https://ci.appveyor.com/project/jesperancinha/vma-archiver)
[![vma-archiver](https://github.com/jesperancinha/vma-archiver/actions/workflows/vma-archiver.yml/badge.svg)](https://github.com/jesperancinha/vma-archiver/actions/workflows/vma-archiver.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/02f12203575c48f5948baf57e8fc27b6)](https://www.codacy.com/gh/jesperancinha/vma-archiver/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/vma-archiver&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/c1b16ed4-8341-416b-9084-c2e0f7b36ffd)](https://codebeat.co/projects/github-com-jesperancinha-vma-archiver-master)
[![Build Status](https://travis-ci.com/jesperancinha/vma-archiver.svg?branch=master)](https://travis-ci.com/jesperancinha/vma-archiver)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/vma-archiver?branch=master)](https://bettercodehub.com/results/jesperancinha/vma-archiver)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/vma-archiver/badge.svg)](https://snyk.io/test/github/jesperancinha/vma-archiver)

[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/vma-archiver/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/vma-archiver?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/vma-archiver/branch/master/graph/badge.svg?token=ZiMdS7cDfF)](https://codecov.io/gh/jesperancinha/vma-archiver)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/02f12203575c48f5948baf57e8fc27b6)](https://www.codacy.com/gh/jesperancinha/vma-archiver/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/vma-archiver&utm_campaign=Badge_Coverage)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/vma-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/vma-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/vma-archiver.svg)](#)

---

## Technologies used

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/kotlin-50.png "Kotlin")](https://kotlinlang.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-50.png "Spring Framework")](https://spring.io/projects/spring-framework)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-boot-50.png "Spring Boot")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-webflux-50.png)](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/apache-maven-50.png "Maven")](https://maven.apache.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/locust-50.png "Locust")](https://locust.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/postgres-50.png "PostgreSQL")](https://www.postgresql.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/testcontainers-50.png "Test containers")](https://www.testcontainers.org/)


---

## Introduction

We are going to make a VMA archiver. VMA in this case does not stand for Video Music Archive anymore. That was version
0.0.0. It now stands for Video Music Awards. So we are going to make a service to register nominees and winners of a VMA
show. In this case, we'll use a fake MTV Awards Show. Instead of using contemporary music, we'll make an MTV awards with
music and artists from the 1920's.

Our application will be built around the Reactive CoRoutine Paradigms. We'll be using five important elements:
Hazelcast, SpringWebFlux, Flow, Kotlin Co-Routines and a nice architecture.

This repo is also the official support repo to my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://itnext.io/coroutines-distributed-cache-resilience-and-replication-in-kotlin-making-a-vmas-application-df563edf8fe8) [Coroutines, Distributed Cache, Resilience, and Replication in Kotlin — Making a VMA’s application](https://itnext.io/coroutines-distributed-cache-resilience-and-replication-in-kotlin-making-a-vmas-application-df563edf8fe8)

#### Walk through

<div align="center">
      <a title="Coroutines, Distributed Cache, Resilience, and Replication in Kotlin DEMO — A VMA’s application" href="https://www.youtube.com/watch?v=hNpEMOFkvv4">
     <img 
          src="https://img.youtube.com/vi/hNpEMOFkvv4/0.jpg" 
          style="width:50%;">
      </a>
</div>

---

## Project Layout

-   [VMA Demo Generation](./vma-demo) - Generates The Demo Awards - It is customizable in terms of categories, music and
  artists.
-   [VMA Common](./vma-common) - A Common Library to support Spring Boot Startup Services.
-   [VMA Play](./vma-play) - A way to play with co-routines separated from the project. It has examples on the main and
  test directories.
-   [VMA Spring CoRoutine Reactive Service](./vma-service-backend) - The reactive service facing the front-end - It posts
  votes in Kafka, registers a new award show directly and reads voting results from the database.
-   [VMA Service Event Listener](./vma-service-event-listener) - Listens to incoming votes sent in a massive load fashion.
-   [Locust](./locust) - The location of locust service. It creates workers in kotlin that simulate random massive voters.

---

## Endpoints

-   [http://localhost:8080/api/vma/welcome](http://localhost:8080/api/vma/welcome)

---

## Install essential libraries and commands

```shell
make install
```

## How to start

```shell
make docker-clean-build-start
```

## Serving Spring Boot (LOCAL)

In order to let Kafka know where to get to locally you need to define locally that `jofisaes_vma_broker` is also
in `127.0.0.1`.

This is done in MAC-OS and Linux machines on `/etc/hosts`:

```text
127.0.0.1   jofisaes_vma_broker
```

---

## Knowledge Cloud

`BuildersKt.class`, `Builders.common`, `withContext`, `ifEmpty`, `tailrec`, `suspend`, `runBlocking`, `shuffled`
, `sortedBy`, `async`
, `await`, `launch`, `delay`, `runBlocking`, `coroutineScope`, `Channel`, `consumeEach`, `produce`, `produceSquares`
, `produceNumbers`, `yield`, `@Volatile`, `withContext`, `Dispatchers.Default`, `Mutex`, `CoroutineScope`
, `counterActor`, `override val`
`massiveRun`,

---

## Knowledge for Integration Tests Cloud

`@Mockk`, `@Test`, `@SpringBootTest`

---

## Java Setup

```shell
sdk install java 17-open
sdk use java 17-open
```

---

## Sequence diagram

To visualize this diagram you may need
the [mermaid-diagrams](https://chrome.google.com/webstore/detail/mermaid-diagrams/phfcghedmopjadpojhmmaffjmfiakfil)
plugin installation.

To visualize it in Intellij, please install the [mermaid plugin](https://mermaid-js.github.io/mermaid/#/).

```mermaid
    sequenceDiagram
        participant VMA Voting Client 1
        participant VMA Voting Client 2
        participant VMA Voting Client n
        participant VMA BE NGINX LB
        participant VMA Services Backend
        participant VMA Kafka Streams
        participant VMA Services Event Listeners
        participant VMA BE HA LB
        participant VMA PostgreSQL
        
        rect rgba(0, 0, 255, .1)
        par
        VMA Voting Client 1-->> VMA BE NGINX LB: Cast vote
        and
        VMA Voting Client 2-->> VMA BE NGINX LB: Cast vote
        and
        VMA Voting Client n-->> VMA BE NGINX LB: Cast vote
        end
        end
        VMA BE NGINX LB-->>VMA Services Backend: Distribute Votes
        VMA Services Backend-->>VMA Kafka Streams: Register Votes
        par
        VMA Kafka Streams-->>VMA Services Backend: Vote registered!
        and
        VMA Kafka Streams-->>VMA Services Event Listeners: Send Register Vote event
        end
        VMA Services Backend-->>VMA BE NGINX LB: Vote registered!
        VMA BE NGINX LB-->>VMA Voting Client 1: Vote registered!
        VMA BE NGINX LB-->>VMA Voting Client 2: Vote registered!
        VMA BE NGINX LB-->>VMA Voting Client n: Vote registered!
        VMA Services Event Listeners-->>VMA PostgreSQL: Create Vote database record
        rect rgba(0, 0, 255, .1)
        par
        VMA Voting Client 1-->> VMA BE NGINX LB: Read votes
        and
        VMA Voting Client 2-->> VMA BE NGINX LB: Read votes
        and
        VMA Voting Client n-->> VMA BE NGINX LB: Read votes
        end
        end
        VMA BE NGINX LB-->>VMA Services Backend: Distribute read votes request
        VMA Services Backend-->>VMA BE HA LB: Request Database Votes
        VMA BE HA LB-->>VMA PostgreSQL: Read Votes
        VMA PostgreSQL-->>VMA BE HA LB: Response with Votes
        VMA BE HA LB-->>VMA Services Backend: Response With Votes
        VMA Services Backend-->>VMA BE NGINX LB: Response With Votes
        VMA BE NGINX LB-->>VMA Voting Client 1: Response With Votes
        VMA BE NGINX LB-->>VMA Voting Client 2: Response With Votes
        VMA BE NGINX LB-->>VMA Voting Client n: Response With Votes
```

---

## References

-   [Using Kotlin Coroutines with Spring](https://hantsy.medium.com/using-kotlin-coroutines-with-spring-d2784a300bda)
-   [Going Reactive with Spring, Coroutines and Kotlin Flow](https://spring.io/blog/2019/04/12/going-reactive-with-spring-coroutines-and-kotlin-flow)
-   [How to Set Up PostgreSQL Cluster using Patroni on Ubuntu 20.04](https://snapshooter.com/learn/postgresql/postgresql-cluster-patroni)
-   [Creating a single HAProxy and two Apache containers with Docker compose](http://www.inanzzz.com/index.php/post/w14j/creating-a-single-haproxy-and-two-apache-containers-with-docker-compose?ref=morioh.com&utm_source=morioh.com)
-   [HAProxy - The Reliable, High Performance TCP/HTTP Load Balancer](https://hub.docker.com/_/haproxy)
-   [Ingress Gateways](https://istio.io/latest/docs/tasks/traffic-management/ingress/ingress-control/)
-   [Amazon Aurora connection management](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/Aurora.Overview.Endpoints.html)
-   [PostgreSQL Load Balancing with HAProxy](https://severalnines.com/resources/database-management-tutorials/postgresql-load-balancing-haproxy)
-   [PostgreSQL HAProxy: Proxy for HA and Load Balance](https://www.alibabacloud.com/blog/postgresql-haproxy-proxy-for-ha-and-load-balance_597618)
-   [How Does a Database Load Balancer Work?](https://severalnines.com/database-blog/how-does-database-load-balancer-work)
-   [HTTP Load Balancing](https://docs.nginx.com/nginx/admin-guide/load-balancer/http-load-balancer/)
-   [Using nginx as HTTP load balancer](http://nginx.org/en/docs/http/load_balancing.html)
-   [Markdown Badges](https://github.com/Ileriayo/markdown-badges)
-   [Kotlin coroutines on Android](https://developer.android.com/kotlin/coroutines)
-   [Full Kotlin Coroutines Design Reference](https://kotlin.github.io/kotlinx.coroutines/)
-   [Kotlin Coroutines Design Document](https://github.com/Kotlin/KEEP/blob/master/proposals/coroutines.md)
-   [Guide to UI programming with coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md)
-   [Coroutine Channels](https://kotlinlang.org/docs/channels.html)
-   [Coroutine](https://en.wikipedia.org/wiki/Coroutine)
-   [Best practices for coroutines in Android](https://developer.android.com/kotlin/coroutines/coroutines-best-practices)
-   [Imagining your Repository Layer with Coroutines](https://proandroiddev.com/imagining-your-repository-layer-with-coroutines-7ee052ee4caa)
-   [17.5. Coroutines](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#reference)
-   [17.5.2. How Reactive translates to Coroutines?](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#kotlin.coroutines.reactive)

## Buy me a coffee

I hope you enjoyed this repository. If you did, you can optionally please buy me a coffee, which supports me in constantly improve and make new free content regularly for everyone. Thank you so much!

[![Buy me a coffee](https://img.buymeacoffee.com/button-api/?text=Buy%20me%20a%20coffee&emoji=&slug=jesperancinha&button_colour=046c46&font_colour=ffffff&font_family=Cookie&outline_colour=ffffff&coffee_colour=FFDD00 "title")](https://www.buymeacoffee.com/jesperancinha)

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/credly-20.png "Credly")](https://www.credly.com/users/joao-esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED "João Esperancinha Homepage")](https://joaofilipesabinoesperancinha.nl/)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)   
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=Across%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Articles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Webapp&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)   
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/github-20.png "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bintray-20.png "BinTray")](https://bintray.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeforces-20.png "Code Forces")](https://codeforces.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)

## Achievements

[![VMware Spring Professional 2021](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/vmware-spring-professional-2021.png "VMware Spring Professional 2021")](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![Oracle Certified Professional, JEE 7 Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-ee-7-application-developer-100.png "Oracle Certified Professional, JEE7 Developer")](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![Oracle Certified Professional, Java SE 11 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-11-developer-100.png "Oracle Certified Professional, Java SE 11 Programmer")](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![IBM Cybersecurity Analyst Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/ibm-cybersecurity-analyst-professional-certificate-100.png "IBM Cybersecurity Analyst Professional")](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![Certified Advanced JavaScript Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/cancanit-badge-1462-100.png "Certified Advanced JavaScript Developer")](https://cancanit.com/certified/1462/)
[![Certified Neo4j Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/professional_neo4j_developer-100.png "Certified Neo4j Professional")](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![Deep Learning](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/deep-learning-100.png "Deep Learning")](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
