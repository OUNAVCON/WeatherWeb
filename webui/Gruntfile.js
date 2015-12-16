"use strict";

module.exports = function (grunt) {
    grunt.loadNpmTasks('grunt-serve');
    grunt.loadNpmTasks("grunt-contrib-clean");
    grunt.loadNpmTasks("grunt-contrib-requirejs");
    grunt.loadNpmTasks("grunt-contrib-concat");
    grunt.loadNpmTasks("grunt-contrib-copy");
    grunt.loadNpmTasks("grunt-contrib-uglify");
    grunt.loadNpmTasks('grunt-contrib-htmlmin');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-text-replace');
    grunt.loadNpmTasks('grunt-typescript');
    grunt.loadNpmTasks('grunt-notify');
    grunt.loadNpmTasks('grunt-sass');
    grunt.loadNpmTasks('grunt-shell');

    // Project configuration.
    grunt.initConfig({
        // Task configuration.
        clean: {
            files: ["dist"]
        },
        concat: {
            dist: {
                src: ["app/bower_components/requirejs/require.js", "<%= concat.dist.dest %>"],
                dest: "dist/require.js"
            }
        },
        requirejs: {
            compile: {
                options: {
                    name: "main",
                    mainConfigFile: "app/main.js",
                    out: "<%= concat.dist.dest %>",
                    optimize: "none"
                }
            }
        },
	serve: {
		options: {
			port: 9000
		}
	},
        uglify: {
            dist: {
                preserveComments: false,
                src: "<%= concat.dist.dest %>",
                dest: "dist/app/js/require.min.js"
            }
        },

        copy: {
            main: {
                files: [
                    {expand: true, src: [
                        'app/**/*.css',
                        'app/**/*.jpg',
                        'app/**/*.png',
                        'app/**/*.gif',
                        'app/**/*.svg',
                        'app/**/*.ico',
                        '!app/bower_components/**/*',
                        '!app/index-dev.html',
                        'app/bower_components/jquery/*',
                        'app/bower_components/bootstrap/dist/css/bootstrap.css',
                        'app/bower_components/bootstrap/dist/fonts/*'
                    ], dest: 'dist/'}
                ]
            }

        },
        sass: {
            dist: {
                options: {
                    outputStyle: 'compressed',
                    sourceComments: 'none'
                },
                files: {
                    'app/css/amdatu.bootstrap.css': 'app/css/amdatu.bootstrap.scss'
                }
            },
            dev: {
                options: {
                    outputStyle: 'nested',
                    sourceComments: 'map'
                },
                files: {
                    'app/css/amdatu.bootstrap.css': 'app/css/amdatu.bootstrap.scss'
                }
            }
        },

        htmlmin: {
            dist: {
                options: {
                    removeComments: true,
                    collapseWhitespace: true
                },
                expand: true,
                cwd: 'app',
                src: [
                    '**/views/*.html'
                ],
                dest: 'dist/app'
            },
            dist_index: {
                options: {
                    removeComments: true,
                    collapseWhitespace: true
                },
                expand: true,
                cwd: 'app',
                src: [
                    'index.html'
                ],
                dest: 'dist/app'
            }
        },

        typescript: {
            dev: {
                src: ['app/**/*.ts'],
                dest: 'dist/app/',
                options: {
                    module: 'amd', //or commonjs
                    target: 'es5', //or es3
                    basePath: 'app',
                    sourceMap: false,
                    declaration: false,
                    watch: {
                        path: 'app',
                        after: ['notify'],
                        atBegin: true              //Run tasks when watcher starts. default false
                    }
                }
            },
            base: {
                src: ['app/**/*.ts'],
                dest: 'dist/app/',
                options: {
                    module: 'amd', //or commonjs
                    target: 'es5', //or es3
                    basePath: 'app',
                    sourceMap: false,
                    declaration: false
                }
            }
        },
        shell: {
            clrscr: {
                options: {
                    stdout: true
                },
                command: 'clear screen'
            }
        },
        notify: {
            done: {
                options: {
                    message: 'Typescript compile complete'
                }
            }
        },

        replace: {
            example: {
                src: ['dist/require.js'],
                dest: 'dist/require.js',
                replacements: [
                  {
                    from: 'http://localhost:8080',
                    to: ''
                  },
                ]
            },
            html: {
              src: 'dist/app/index.html',
              dest: 'dist/app/index.html',
              replacements: [
                {
                  from: 'bower_components/requirejs/require.js',
                  to: 'js/require.min.js'
                }
              ]
            }
        },

        watch: {
            base: {
                files: 'app/**/*.ts',
                tasks: ['shell:clrscr', 'typescript:base']
            },
            scss: {
                files: 'app/**/*.scss',
                tasks: ['shell:clrscr', 'sass:dev','copy:main']
            }
        }
    });

    grunt.registerTask("default", ["clean", "typescript:base", "sass:dist", "htmlmin", "concat", "requirejs", "replace", "uglify", "copy:main"]);
    grunt.registerTask("dev", ["typescript", "sass:dev", "serve", "watch"]);
};
