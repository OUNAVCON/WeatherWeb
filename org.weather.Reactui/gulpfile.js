var gulp = require('gulp'),
    rimraf = require('gulp-rimraf'),
    http = require('http'),
    ecstatic = require('ecstatic'),
    typescript = require('gulp-type'),
    rjs = require('gulp-requirejs'),
    ngmin = require('gulp-ngmin'),
    uglify = require('gulp-uglify'),
    jasminePhantomJs = require('gulp-jasmine2-phantomjs'),
    minifycss = require('gulp-minify-css'),
    bowerFiles = require('gulp-bower-files'),
    replace = require('gulp-replace'),
    sass = require('gulp-sass'),
    karma = require('gulp-karma'),
    connect = require('gulp-connect'),
    runSequence = require('run-sequence'),
    util = require('gulp-util'),
    bower = require('gulp-bower');


var src = 'app';
//var testSrc = 'web-test';
var output = 'dist/app';
var typesSrc = 'types';
var tsScriptFiles = [typesSrc + '/**/*.ts', src + '/**/*.ts', testSrc + '/types/**/*.ts'];
var sassFiles = src + '/**/*.scss';
var cssFiles = output + '/**/*.css';
var testRunnerFiles = testSrc + '/**/*.html';
var htmlFiles = src + '/**/*.html';
var fontFiles = src + '/**/*.+(eot|svg|ttf|woff)';
var imageFiles = src + '/**/images/**/*';
var txtFiles = src + '/**/*.txt';

function copy(source) {
    return gulp.src(source).pipe(gulp.dest(output));
}

gulp.task('default', ['clean'], function () {
    return runSequence(['watch', 'karma-test-run']);
});

gulp.task('dist', ['clean'], function () {
    return gulp.start('buildDist');
});

gulp.task('test-using-html-runner', ['compileTS', 'copyBowerLibs'], function () {
    return gulp.src(testRunnerFiles).pipe(jasminePhantomJs());
});

gulp.task('test', ['clean'], function () {
    return runSequence('compileTS', 'copyAssets', 'copyImages', 'compileSass', 'karma-test-once');
});

gulp.task('clean', function () {
    return gulp.src(['!./' + output + '/.gitignore', output + "/*"], {read: false})
        .pipe(rimraf());
});

gulp.task('watch', ['compileTS', 'copyAssets', 'copyImages', 'compileSass', 'serve'], function (done) {
    gulp.watch(sassFiles, ['compileSass']);
    gulp.watch(tsScriptFiles, ['compileTS']);
    gulp.watch(htmlFiles, ['copyHtml']);
    gulp.watch(fontFiles, ['copyFonts']);
    gulp.watch(txtFiles, ['copyTxt']);
    return done();
});

var tsProject = typescript.createProject({
    declarationFiles: false,
    noExternalResolve: true,
    module: "amd",
    target: "ES5"
});

gulp.task('compileTS', function () {
    var tsResult = gulp.src(tsScriptFiles)
        .pipe(typescript(tsProject));

    return tsResult.js
        .pipe(gulp.dest(output))
        .pipe(connect.reload());
});

gulp.task('buildDist', ['copyHtml', 'copyImages', 'copyFonts', 'copyTxt', 'copyBowerLibs'], function () {
    return gulp.start('distCompile');
});

gulp.task('distCompile', ['distScripts', 'distStyles']);

gulp.task('distScripts', ['compileTS', 'replaceParams'], function () {
    return rjs({
        baseUrl: output,
        name: 'main',
        out: 'main.js',
        mainConfigFile: output + '/main.js',
        include: ['es.ui']
    })
        .pipe(ngmin())
        .pipe(uglify({
            mangle: false,
            output: {
                ascii_only: true
            }
        })) // Since ng-min doesn't seem to catch all minification problems, don't mangle identifiers
        .pipe(gulp.dest(output));
});

gulp.task('distStyles', ['compileSass'], function () {
    return gulp.src(cssFiles)
        .pipe(minifycss())
        .pipe(gulp.dest(output));
    ;
});

gulp.task('replaceParams', ['compileTS'], function () {
    function contains(a, obj) {
        var i = a.length;
        while (i--) {
            if (a[i] === obj) {
                return true;
            }
        }
        return false;
    }
	
	console.log('org.syntech.es.ui::gulpfile.js:: process.argv = ' + process.argv); 
	console.log('org.syntech.es.ui::gulpfile.js:: util.env.buildName = ' + util.env.buildName);

    var buildName;
    if (!contains(process.argv, '--buildName')) {
        buildName = 'Local Build';
    } else if (!util.env.buildName) {
        buildName = 'Local Build';
    } else {
        buildName = util.env.buildName;
    }
	
	console.log('org.weather::gulpfile.js:: buildName = ' + buildName);
	
    return gulp.src(output + '/es.ui.js')
        .pipe(replace('//localhost:9000', ''))
        .pipe(replace('Local build', buildName))
        .pipe(gulp.dest(output));
});

gulp.task('compileSass', function () {
    return gulp.src(sassFiles)
        .pipe(sass())
        .on('error', console.error.bind(console))
        .pipe(gulp.dest(output))
        .pipe(connect.reload());
});

gulp.task('copyAssets', ['copyBowerLibs', 'copyHtml', 'copyFonts', 'copyTxt']);

gulp.task('bowerInstall', function () {
    return bower();
});

gulp.task("copyBowerLibs", ['bowerInstall'], function () {
    return bowerFiles().pipe(gulp.dest(output + "/lib"));
});

gulp.task('copyHtml', function () {
    return copy(htmlFiles)
        .pipe(connect.reload());
});
gulp.task('copyImages', function () {
    return copy(imageFiles);
});
gulp.task('copyFonts', function () {
    return copy(fontFiles);
});
gulp.task('copyTxt', function () {
    return copy(txtFiles);
});

gulp.task('serve', function (done) {
    http.createServer(ecstatic({ root: __dirname + "/home" })).listen(9090);
    return done();
});

/** Testing Tasks **/
var karmaTest = function (action) {
    //The file passed in here intentionally does not exist due to a bug with karma
    return gulp.src("i-do-not-exist.js")
        .pipe(karma({
            configFile: 'web-test/karma.conf.js',
            action: action
        }))
        .on('error', function (err) {
            console.log(err);
            throw err;
        });
};


gulp.task('karma-test-once', function () {
    karmaTest('run');
});

/** Dev Server/Live-reload Tasks **/
gulp.task('server-start', function () {
    connect.server({
        root: __dirname + "/dist/app",
        port: 9090,
        livereload: true
    });
});

gulp.task('dev', function () {
    return gulp.start('watch-dev');
});

gulp.task('karma-test-run', function () {
    karmaTest('watch');
});

gulp.task('watch-dev', ['compileTS', 'copyAssets', 'copyImages', 'compileSass', 'karma-test-run', 'server-start'], function (done) {
    gulp.watch(sassFiles, ['compileSass']);
    gulp.watch(tsScriptFiles, ['compileTS']);
    gulp.watch(htmlFiles, ['copyHtml']);
    return done();
});
