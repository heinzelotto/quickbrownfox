(defproject quickbrownfox "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.github.halgari/fn-fx "0.3.0-SNAPSHOT"]]
  :main ^:skip-aot quickbrownfox.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
