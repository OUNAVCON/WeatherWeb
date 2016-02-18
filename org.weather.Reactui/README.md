# org.weather

This project holds the main UI for the Weather Project. It contains the frontend HTML and scripts.

## Frontend technology
The UI is based on [HTML5](http://diveintohtml5.info), [AngularJS](https://angularjs.org), [RequireJS](http://requirejs.org), [Bower](http://bower.io), [TypeScript](http://diveintohtml5.info) and [Foundation](http://foundation.zurb.com). Layout of this project:

- ```src```: Java code for cross-domain filter, used during development (see later in this readme)
- ```static```: contents of this folder are served (using a local http server during dev, using OSGi HttpService in production). Everything in this folder is derived: do not commit it or modify it manually.
- ```web```: source folder for all web-assets (HTML templates, TypeScript sources, styling etc.). A build tool picks up these assets and modifies/compiles them into the ```static``` folder.
- ```web/types```: contains TypeScript definition files. These are 'header' files that contain type declarations for external JavaScript libs.
- ```bower_components```: directory managed by Bower (not committed in Git), containing external clientside JS libs, like AngularJS.
- ```node_modules```: directory managed by NodeJS (not committed in Git, since it may contain OS specific code), containing dependencies for the JS build system.
- ```package.json```: NodeJS package file describing all the dependencies for the JS build system.
- ```bower.json```: Bower file describing all dependencies on clientside JS libs.
- ```gulpfile.js```: buildfile for web-assets.
- ```assets.bnd```: descriptor that puts everything from ```static``` into the ```org.weather.ui.assets``` bundle, with Amdatu Resource handler headers so they are served through the HttpService.

## Setting up frontend development support
During development, [Gulp](http://gulpjs.com) is used to support tasks like compiling the TypeScript, pre-processing styling etc.
Gulp is a NodeJS-based JavaScript build tool.
First, make sure to install version 0.10.33 of Node.JS [found here](http://nodejs.org/dist/v0.10.33/). This is the version currently being used in the project.
The latest versoin of Node.JS can be downloaded [here](http://nodejs.org/download/) but is not recommended.

Then, install Gulp and Bower:
```
npm install -g gulp bower
```

Next, make sure you are in the ```org.weather.ui``` directory and run:

```
npm install
```

The first command makes sure all dependencies for the Gulp build are installed, as defined in the ```package.json``` descriptor. They end up in ```/node_modules```, which should not be committed. Whenever changes are made in ```packages.json```, you need to reinstall. 

## Frontend dev workflow
Once you have installed the frontend tooling, you can simply run ```gulp```. It will run all the relevant task, and starts watching the files in the ```web``` directory. Whenever something changes, Gulp will re-run the relevant tasks (like compiling the TypeScript files). It also starts a lightweight http server that serves the compiled assets from the ```static``` directory. The result can be accessed through:

[http://localhost:9090/home/](http://localhost:9090/home/)

There is also a task for offline builds, which can be invoked with ```gulp dist```. The assets are now concatenated and minified, resulting in a production-ready build in the ```static``` directory that can be served by the OSGi HttpService through the Amdatu Resource Handler. This task is also used in the CI build.

## Adding additional JS libraries
When you want to add a new JS lib, find it with Bower (you can use ```bower search```). After you found the right Bower package, install it:

```
bower install my-pkg-name --save
```

This command downloads the external JS dependencies into ```bower_components``` and also saves the dependency reference inside ```bower.json```, so the process is repeatable. Commit only the ```bower.json``` file. The Gulp build automatically picks up any dependencies added through Bower and places them in the ```lib``` in the output. Whenever changes are made in ```bower.json```, Gulp will install them automatically.

## Unit testing
For frontend unit testing, we use [Jasmine 2.0](http://jasmine.github.io/2.0/introduction.html). Gulp is setup to automatically run tests whenever code changes. Adding new tests requires the following steps:

- Create a new test file in the ```/test``` subdirectory of the module under test
- Write the test, using Jasmine's DSL (e.g. ```describe```, ```it```). Tests can be written in TypeScript just as production code. Make sure to use a unique name in the toplevel ```describe```, otherwise testresults will overwrite eachother in the build.
- That's it :) These tests are automatically picked up by karma test runner therefore test-runner.html no longer requires to be updated.
- To run the tests one can use inteliJ (select karma.conf and select run) or use gulp. See MFM-1426 for more details.

NOTE::
 - If the directory structure changes or a new module is added then this needs to be reflected in karma.conf
 - Any dependencies change in main.ts/js need to be reflected in web-test/testMain.js

Check [https://docs.angularjs.org/guide/unit-testing](https://docs.angularjs.org/guide/unit-testing) for advice on how to write good unit tests in combination with Angular.

## Cross domain filter
Since the UI runs on a different than the REST backend during development, we need to add so-called [CORS-headers](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS) headers. Otherwise the UI would not be allowed to perform AJAX requests to the REST backend. Therefore, a ```org.weather.ui``` bundle is emitted. It contains a Servlet Filter that handles the OPTIONS request performed by browser because of cross-domain origins.

Obviously, this bundle should *only* be used during development. In a production deployment scenario, the static assets are served by the same HttpService that handles the REST endpoints. Hence, the browser can perform calls from the UI to the endpoints without any CORS-headers present.


