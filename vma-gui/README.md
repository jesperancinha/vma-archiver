# VmaGui

## Steps to build

```shell
ng generate component main
ng generate component vote-following
ng generate component vote-result
```

```shell
npm install -g npm-check-updates
ncu -u
npm update
```

```shell
npm install @stomp/stompjs --save
npm install sockjs-client --save
npm install tslib --save
npm install @types/sockjs-client --save
npm install @angular/material --save
npm install rxjs --save
```

```shell
npm install -g n
sudo n latest
```

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 12.2.7.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
