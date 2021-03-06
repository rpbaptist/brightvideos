(defproject brightvideos "0.1.0-SNAPSHOT"
  :description "Something of a reddit clone for sharing interesting video's."
  :url "http://www.tomkruijsen.nl"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"
                  :exclusions [org.clojure/tools.reader]]
                 [reagent "0.5.1"]
                 [devcards "0.2.1-6"]
                 [cljsjs/react "0.14.3-0"]
                 [cljsjs/react-dom "0.14.3-1"]
                 [cljsjs/react-dom-server "0.14.3-0"]]

  :plugins [[lein-figwheel "0.5.0-6"]
            [lein-cljsbuild "1.1.2" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {
    :builds [
      { :id "devcards"
        :source-paths ["src"]
        :figwheel { :devcards true } ;; <- note this
        :compiler {
          :main brightvideos.core
          :asset-path "js/compiled/devcards_out"
          :output-to  "resources/public/js/compiled/brightvideos_devcards.js"
          :output-dir "resources/public/js/compiled/devcards_out"
          :source-map-timestamp true }}
      { :id "dev"
        :source-paths ["src"]
        :figwheel {:on-jsload "brightvideos.core/on-js-reload"}
        :compiler {
          :main brightvideos.core
          :asset-path "js/compiled/out"
          :output-to "resources/public/js/compiled/brightvideos.js"
          :output-dir "resources/public/js/compiled/out"
          :source-map-timestamp true}}
      { :id "min"
        :source-paths ["src"]
        :compiler {
          :output-to "resources/public/js/compiled/brightvideos.js"
          :main brightvideos.core
          :optimizations :advanced
          :pretty-print false}}]}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"]}) ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
