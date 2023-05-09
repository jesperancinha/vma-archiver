# vma-archiver

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=%20Checkout%20this%20%40github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/vma-archiver)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=VMA%20Archiver%20📼%20&color=informational)](https://github.com/jesperancinha/vma-archiver)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/vma-archiver.svg)](#)

[![CircleCI](https://circleci.com/gh/jesperancinha/vma-archiver.svg?style=svg)](https://circleci.com/gh/jesperancinha/vma-archiver)
[![Build status](https://ci.appveyor.com/api/projects/status/x2q0u359vwgl6rrl?svg=true)](https://ci.appveyor.com/project/jesperancinha/vma-archiver)

[![vma-archiver](https://github.com/jesperancinha/vma-archiver/actions/workflows/vma-archiver.yml/badge.svg)](https://github.com/jesperancinha/vma-archiver/actions/workflows/vma-archiver.yml)
[![e2e-vma-archiver-app](https://github.com/jesperancinha/vma-archiver/actions/workflows/vma-archiver-e2e.yml/badge.svg)](https://github.com/jesperancinha/vma-archiver/actions/workflows/vma-archiver-e2e.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/02f12203575c48f5948baf57e8fc27b6)](https://www.codacy.com/gh/jesperancinha/vma-archiver/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/vma-archiver&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/vma-archiver/badge.svg)](https://snyk.io/test/github/jesperancinha/vma-archiver)

[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/vma-archiver/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/vma-archiver?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/vma-archiver/branch/master/graph/badge.svg?token=ZiMdS7cDfF)](https://codecov.io/gh/jesperancinha/vma-archiver)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/02f12203575c48f5948baf57e8fc27b6)](https://www.codacy.com/gh/jesperancinha/vma-archiver/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/vma-archiver&utm_campaign=Badge_Coverage)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/vma-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/vma-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/vma-archiver.svg)](#)

---

## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

## Introduction

We are going to make a VMA archiver. VMA in this case does not stand for Video Media Archive anymore. That was version
0.0.0. It now stands for Video Music Awards. So we are going to make a service to register nominees and winners of a VMA
show. In this case, we'll use a fake MTV Awards Show. Instead of using contemporary music, we'll make an MTV awards with
music and artists from the 1920's.

Our application will be built around the Reactive CoRoutine Paradigms. We'll be using five important elements:
Hazelcast, SpringWebFlux, Flow, Kotlin Co-Routines and a nice architecture.

This repo is also the official support repo to my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://itnext.io/coroutines-distributed-cache-resilience-and-replication-in-kotlin-making-a-vmas-application-df563edf8fe8) [Coroutines, Distributed Cache, Resilience, and Replication in Kotlin — Making a VMA’s application](https://itnext.io/coroutines-distributed-cache-resilience-and-replication-in-kotlin-making-a-vmas-application-df563edf8fe8)

#### - Stable releases

-   [0.0.0](https://github.com/jesperancinha/vma-archiver/tree/0.0.0) - [36e8f4b35b0a1226d440d6698194e989b8601c1b](https://github.com/jesperancinha/vma-archiver/tree/0.0.0) - First Video Media Archive version / Gradle / Spring 5 / JDK 11
-   [1.0.0](https://github.com/jesperancinha/vma-archiver/tree/1.0.0) - [992cdb7f6262b8e366142814f6b6547ae922cf52](https://github.com/jesperancinha/vma-archiver/tree/1.0.0) - Video Music Awards App / JDK 17 / Coroutines / Kotlin 1.7.20 / Spring Boot 2.7.4 / Docker with fixed IP's
-   [2.0.0](https://github.com/jesperancinha/vma-archiver/tree/2.0.0) - [17ae13b0c8c1bdc282225ef1515215f7f5906997](https://github.com/jesperancinha/vma-archiver/tree/2.0.0) - Video Music Awards App / JDK 17 / Coroutines / Kotlin 1.7.20 / Spring Boot 3.0.1 / Docker with dynamic IP's

#### - [Walk through](https://www.youtube.com/watch?v=hNpEMOFkvv4)

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

In order to let Kafka know where to get to locally you need to define locally that `jofisaes-vma-broker` is also
in `127.0.0.1`.

This is done in MAC-OS and Linux machines on `/etc/hosts`:

```text
127.0.0.1   jofisaes-vma-broker
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

#### How to run

1.  Start all containers
```shell
make dcup-full
```

>If it fails, you can always try `make dcup`.
---
2.  Start Locust
```shell
make locust
````
---
3.  Cast your vote   
Go to [http://localhost:8080](http://localhost:8080) and cast your votes
---
4.  Wait for locust to stop and check the result   
Go to [http://localhost:8080/result](http://localhost:8080/result)

#### Swagger tests

You can make tests for this application using the Swagger UI at:

-   [WebFlux Reactive Backend](http://localhost:8080/api/vma/webjars/swagger-ui/index.html)

---

## References

-   [How to fix the LEADER_NOT_AVAILABLE error in Kafka?](https://www.hadoopinrealworld.com/how-to-fix-the-leader_not_available-error-in-kafka)
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

## About me

<div align="center">

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-100/JEOrgLogo-27.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![](https://img.shields.io/badge/youtube-%230077B5.svg?style=for-the-badge&logo=youtube&color=FF0000)](https://www.youtube.com/@joaoesperancinha)
[![](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@jofisaes)
[![](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-%230077B5.svg?style=for-the-badge&logo=buymeacoffee&color=yellow)](https://www.buymeacoffee.com/jesperancinha)
[![](https://img.shields.io/badge/Twitter-%230077B5.svg?style=for-the-badge&logo=twitter&color=white)](https://twitter.com/joaofse)
[![](https://img.shields.io/badge/Mastodon-%230077B5.svg?style=for-the-badge&logo=mastodon&color=afd7f7)](https://masto.ai/@jesperancinha)
[![](https://img.shields.io/badge/Facebook-%230077B5.svg?style=for-the-badge&logo=facebook&color=3b5998)](https://www.facebook.com/joaofisaes/)
[![](https://img.shields.io/badge/Sessionize-%230077B5.svg?style=for-the-badge&logo=sessionize&color=cffff6)](https://sessionize.com/joao-esperancinha)
[![](https://img.shields.io/badge/Instagram-%230077B5.svg?style=for-the-badge&logo=instagram&color=purple)](https://www.instagram.com/joaofisaes)
[![](https://img.shields.io/badge/Tumblr-%230077B5.svg?style=for-the-badge&logo=tumblr&color=192841)](https://jofisaes.tumblr.com)
[![](https://img.shields.io/badge/Spotify-1ED760?style=for-the-badge&logo=spotify&logoColor=white)](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm)
[![](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/joaoesperancinha/)
[![](https://img.shields.io/badge/Xing-%230077B5.svg?style=for-the-badge&logo=xing&color=064e40)](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![](https://img.shields.io/badge/YCombinator-%230077B5.svg?style=for-the-badge&logo=ycombinator&color=d0d9cd)](https://news.ycombinator.com/user?id=jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
[![](https://img.shields.io/badge/bitbucket-%230077B5.svg?style=for-the-badge&logo=bitbucket&color=blue)](https://bitbucket.org/jesperancinha)
[![](https://img.shields.io/badge/gitlab-%230077B5.svg?style=for-the-badge&logo=gitlab&color=orange)](https://gitlab.com/jesperancinha)
[![](https://img.shields.io/badge/Sonatype%20Search%20Repos-%230077B5.svg?style=for-the-badge&color=red)](https://central.sonatype.com/search?smo=true&q=org.jesperancinha)
[![](https://img.shields.io/badge/Stack%20Overflow-%230077B5.svg?style=for-the-badge&logo=stackoverflow&color=5A5A5A)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![](https://img.shields.io/badge/Credly-%230077B5.svg?style=for-the-badge&logo=credly&color=064e40)](https://www.credly.com/users/joao-esperancinha)
[![](https://img.shields.io/badge/Coursera-%230077B5.svg?style=for-the-badge&logo=coursera&color=000080)](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![](https://img.shields.io/badge/Docker-%230077B5.svg?style=for-the-badge&logo=docker&color=cyan)](https://hub.docker.com/u/jesperancinha)
[![](https://img.shields.io/badge/Reddit-%230077B5.svg?style=for-the-badge&logo=reddit&color=black)](https://www.reddit.com/user/jesperancinha/)
[![](https://img.shields.io/badge/Hackernoon-%230077B5.svg?style=for-the-badge&logo=hackernoon&color=0a5d00)](https://hackernoon.com/@jesperancinha)
[![](https://img.shields.io/badge/Dev.TO-%230077B5.svg?style=for-the-badge&color=black&logo=dev.to)](https://dev.to/jofisaes)
[![](https://img.shields.io/badge/Code%20Project-%230077B5.svg?style=for-the-badge&logo=codeproject&color=063b00)](https://www.codeproject.com/Members/jesperancinha)
[![](https://img.shields.io/badge/Free%20Code%20Camp-%230077B5.svg?style=for-the-badge&logo=freecodecamp&color=0a5d00)](https://www.freecodecamp.org/jofisaes)
[![](https://img.shields.io/badge/Hackerrank-%230077B5.svg?style=for-the-badge&logo=hackerrank&color=1e2f97)](https://www.hackerrank.com/jofisaes)
[![](https://img.shields.io/badge/LeetCode-%230077B5.svg?style=for-the-badge&logo=leetcode&color=002647)](https://leetcode.com/jofisaes)
[![](https://img.shields.io/badge/Codewars-%230077B5.svg?style=for-the-badge&logo=codewars&color=722F37)](https://www.codewars.com/users/jesperancinha)
[![](https://img.shields.io/badge/CodePen-%230077B5.svg?style=for-the-badge&logo=codepen&color=black)](https://codepen.io/jesperancinha)
[![](https://img.shields.io/badge/HackerEarth-%230077B5.svg?style=for-the-badge&logo=hackerearth&color=00035b)](https://www.hackerearth.com/@jofisaes)
[![](https://img.shields.io/badge/Khan%20Academy-%230077B5.svg?style=for-the-badge&logo=khanacademy&color=00035b)](https://www.khanacademy.org/profile/jofisaes)
[![](https://img.shields.io/badge/Pinterest-%230077B5.svg?style=for-the-badge&logo=pinterest&color=FF0000)](https://nl.pinterest.com/jesperancinha)
[![](https://img.shields.io/badge/Quora-%230077B5.svg?style=for-the-badge&logo=quora&color=FF0000)](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![](https://img.shields.io/badge/Google%20Play-%230077B5.svg?style=for-the-badge&logo=googleplay&color=purple)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![](https://img.shields.io/badge/Coderbyte-%230077B5.svg?style=for-the-badge&color=blue&logo=coderbyte)](https://coderbyte.com/profile/jesperancinha)
[![](https://img.shields.io/badge/InfoQ-%230077B5.svg?style=for-the-badge&color=blue&logo=coderbyte)](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![](https://img.shields.io/badge/OCP%20Java%2011-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![](https://img.shields.io/badge/OCP%20JEE%207-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![](https://img.shields.io/badge/VMWare%20Spring%20Professional%202021-%230077B5.svg?style=for-the-badge&logo=spring&color=064e40)](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![](https://img.shields.io/badge/IBM%20Cybersecurity%20Analyst%20Professional-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![](https://img.shields.io/badge/Deep%20Learning-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
[![](https://img.shields.io/badge/Certified%20Neo4j%20Professional-%230077B5.svg?style=for-the-badge&logo=neo4j&color=064e40)](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![](https://img.shields.io/badge/Certified%20Advanced%20JavaScript%20Developer-%230077B5.svg?style=for-the-badge&logo=javascript&color=064e40)](https://cancanit.com/certified/1462/)
[![](https://img.shields.io/badge/Kong%20Champions-%230077B5.svg?style=for-the-badge&logo=kong&color=064e40)](https://konghq.com/kong-champions)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=064e40&style=for-the-badge "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=064e40&style=for-the-badge "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=orange&style=for-the-badge "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)

</div>
