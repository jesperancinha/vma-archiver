b: build-npm build-maven
buildw:
	cd vma-service && ./mvnw clean install
build:
	mvn clean install
build-maven:
	mvn clean install -DskipTests
build-npm:
	cd vma-gui && yarn && npm run build
	bash cleanup.sh
	mv vma-gui/dist docker-images/nginx/
test:
	mvn test
test-maven:
	mvn test
local: no-test
	mkdir -p bin
no-test:
	mvn clean install -DskipTests
docker:
	docker-compose up -d --build --remove-orphans
docker-databases: stop local
build-images:
build-docker: stop no-test
	docker-compose up -d --build --remove-orphans
show:
	docker ps -a  --format '{{.ID}} - {{.Names}} - {{.Status}}'
docker-clean:
	docker-compose rm -svf
docker-delete-idle:
	docker ps --format '{{.ID}}' -q --filter="name=jofisaes_vma_" | xargs -I {}  docker rm {}
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q --filter="name=jofisaes_vma_" | xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' -q --filter="name=jofisaes_vma_" | xargs -I {}  docker rm {}
docker-cleanup: docker-delete
	docker images -q | xargs docker rmi
docker-delete-apps: stop
docker-clean-build-start: docker-clean b docker
docker-clean-start: docker-clean docker
docker-psql-cluster:
	docker-compose down --remove-orphans
	docker-compose up -d --build jofisaes_vma_haproxy_lb jofisaes_vma_etcd
	docker-compose up -d --build jofisaes_vma_postgres_1
	docker-compose up -d --build jofisaes_vma_postgres_2 jofisaes_vma_postgres_3
docker-no-app: docker-psql-cluster
	docker-compose up -d --build jofisaes_schemaregistry jofisaes_vma_zookeeper jofisaes_vma_broker
docker-stop-apps:
	docker stop jofisaes_vma_nginx_lb
	docker stop jofisaes_vma_backend_img_1
	docker stop jofisaes_vma_backend_img_2
	docker stop jofisaes_vma_backend_img_3
	docker stop jofisaes_vma_listener
docker-start-kafka:
	docker start jofisaes_vma_zookeeper
	docker start jofisaes_vma_broker
docker-stats:
	docker stats
prune-all: docker-delete
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes
stop:
	docker-compose down --remove-orphans
install:
	/usr/bin/python3 -m pip install --upgrade pip
	pip3 install requests
	pip3 install locust
case:
	cd vma-demo && make create-vmas
locust: case
	cd locust && locust --host=localhost --headless -u 10 -r 10 --run-time 30s --csv vma-awards --exit-code-on-error 0
count-votes:
	curl -i -X POST http://localhost:8080/api/vma/voting/count
vma-wait:
	bash vma_wait.sh
db-wait:
	bash db_wait.sh
dcup-light: stop
	docker-compose up -d --build --remove-orphans jofisaes_vma_postgres_1 jofisaes_vma_postgres_2 jofisaes_vma_postgres_3 jofisaes_vma_haproxy_lb jofisaes_vma_etcd
	make db-wait
dcd: stop
dcup: dcd docker-clean docker vma-wait
dcup-full: docker-clean-build-start vma-wait
cypress-open:
	cd e2e && yarn && npm run cypress
cypress:
	cd e2e && yarn && npm run cypress:run
cypress-chrome:
	cd e2e && yarn && npm run cypress:run:chrome
cypress-firefox:
	cd e2e && yarn && npm run cypress:run:firefox
demo: dcup cypress
demo-full: dcup-full cypress
demo-full-manual: dcup-full cypress-open
kafka:
	docker-compose rm -svf jofisaes_vma_zookeeper
	docker-compose rm -svf jofisaes_vma_broker
	docker-compose rm -svf jofisaes_schemaregistry
	docker-compose up -d --build --remove-orphans jofisaes_vma_zookeeper jofisaes_vma_broker jofisaes_schemaregistry
	bash kafka_wait.sh
