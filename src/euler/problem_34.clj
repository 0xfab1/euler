(def facts
  (->> (range 1 10)
       (reductions * 1)
       (interleave (range 10))
       (apply hash-map)))

(defn- sumo-fact [x]
  (->> (str x)
       (map #(- (int %) 48))
       (map facts)
       (apply +)))

(->> (range 3 750000)  ; the cutoff point of the two curves should be slightly below this value
     (map #(identity [% (sumo-fact %)]))
     (filter #(= (% 0) (% 1)))
     (map second))
