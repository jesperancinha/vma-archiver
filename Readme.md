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

<details>
<summary><b>Stable Releases</b></summary>

---
This repo is also the official support repo to my article on medium:

[![](https://img.shields.io/badge/Coroutines,%20Distributed%20Cache,%20Resilience,%20and%20Replication%20'in%20'Kotlin%20'—%20'Making%20'a%20'VMA’s%20'application-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://itnext.io/coroutines-distributed-cache-resilience-and-replication-in-kotlin-making-a-vmas-application-df563edf8fe8)

#### - Stable releases

-   [0.0.0](https://github.com/jesperancinha/vma-archiver/tree/0.0.0) - [36e8f4b35b0a1226d440d6698194e989b8601c1b](https://github.com/jesperancinha/vma-archiver/tree/0.0.0) -
  First Video Media Archive version / Gradle / Spring 5 / JDK 11
-   [1.0.0](https://github.com/jesperancinha/vma-archiver/tree/1.0.0) - [992cdb7f6262b8e366142814f6b6547ae922cf52](https://github.com/jesperancinha/vma-archiver/tree/1.0.0) -
  Video Music Awards App / JDK 17 / Coroutines / Kotlin 1.7.20 / Spring Boot 2.7.4 / Docker with fixed IP's
-   [2.0.0](https://github.com/jesperancinha/vma-archiver/tree/2.0.0) - [17ae13b0c8c1bdc282225ef1515215f7f5906997](https://github.com/jesperancinha/vma-archiver/tree/2.0.0) -
  Video Music Awards App / JDK 17 / Coroutines / Kotlin 1.7.20 / Spring Boot 3.0.1 / Docker with dynamic IP's
---
</details>

<details>
<summary><b><a href="https://www.youtube.com/watch?v=hNpEMOFkvv4">Walk Through</a>
</b></summary>

---
<div align="center">
      <a title="Coroutines, Distributed Cache, Resilience, and Replication in Kotlin DEMO — A VMA’s application" href="https://www.youtube.com/watch?v=hNpEMOFkvv4">
     <img 
          src="https://img.youtube.com/vi/hNpEMOFkvv4/0.jpg" 
          style="width:50%;">
      </a>
</div>

---
</details>
<details>
<summary><b>Knowledge Cloud</b></summary>

---
##### Knowledge Cloud

`BuildersKt.class`, `Builders.common`, `withContext`, `ifEmpty`, `tailrec`, `suspend`, `runBlocking`, `shuffled`
, `sortedBy`, `async`
, `await`, `launch`, `delay`, `runBlocking`, `coroutineScope`, `Channel`, `consumeEach`, `produce`, `produceSquares`
, `produceNumbers`, `yield`, `@Volatile`, `withContext`, `Dispatchers.Default`, `Mutex`, `CoroutineScope`
, `counterActor`, `override val`
`massiveRun`,

---

##### Knowledge for Integration Tests Cloud

`@Mockk`, `@Test`, `@SpringBootTest`
----
</details>

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

## How to run

In general:

```shell
make docker-clean-build-start
```

<details>
<summary><b>Start all containers</b></summary>

---
```shell
make dcup-full
```

> If it fails, you can always try `make dcup`.
---
</details>

<details>
<summary><b>Start Locust</b></summary>

---
```shell
make locust
````
---
</details>

<details>
<summary><b>Cast your vote</b></summary>

---
-   Go to [http://localhost:8080](http://localhost:8080) and cast your votes
---
</details>


<details>
<summary><b>Wait for locust to stop and check the result</b></summary>

---
-   Go to [http://localhost:8080/result](http://localhost:8080/result)
---
</details>

<details>
<summary><b>Swagger tests</b></summary>

---
##### You can make tests for this application using the Swagger UI at

-   [WebFlux Reactive Backend](http://localhost:8080/api/vma/webjars/swagger-ui/index.html)
---
</details>

<details>
<summary><b>Endpoints</b></summary>

---
-   [http://localhost:8080/api/vma/welcome](http://localhost:8080/api/vma/welcome)
---
</details>

<details>
<summary><b>Install essential libraries and commands</b></summary>

---
```shell
make install
```
---
</details>

<details>
<summary><b>Serving Spring Boot (LOCAL)</b></summary>

---
In order to let Kafka know where to get to locally you need to define locally that `jofisaes-vma-broker` is also
in `127.0.0.1`.

This is done in MAC-OS and Linux machines on `/etc/hosts`:

```text
127.0.0.1   jofisaes-vma-broker
```
---
</details>

<details>
<summary><b>Java setup</b></summary>

---
```shell
sdk install java 17-open
sdk use java 17-open
```
---
</details>

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

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
