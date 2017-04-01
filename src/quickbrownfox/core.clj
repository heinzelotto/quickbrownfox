(ns quickbrownfox.core
  (:require [fn-fx.fx-dom :as dom]
            [fn-fx.diff :refer [component defui render should-update?]]
            [fn-fx.controls :as ui])
  (:gen-class))

(load "ui")

(defn -main
  [& args]
  (print "Hello World!")
  (gui-main))
