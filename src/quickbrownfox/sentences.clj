(in-ns 'quickbrownfox.core)

(def alphabet (seq "abcdefghijklmnopqrstuvwxyzäöüß"))

(defn avail-letters [s]
  (apply list (clojure.set/difference
         (into (sorted-set) alphabet)
         (into (sorted-set) (seq s)))))

(defn remove-first
  [s l]
  (let [[n m] (split-with (partial not= l) s)] (concat n (rest m))))

(defn extra-letters [s]
  (sort (loop [ex (seq s)
               alph alphabet]
          (if (empty? alph)
            ex
            (recur (remove-first ex (first alph))
                   (rest alph))))))
