(in-ns 'quickbrownfox.core)

(load "sentences")

(def firebrick
  (ui/color :red 0.69 :green 0.13 :blue 0.13))

;; The main login window component, notice the authed? parameter, this defines a function
;; we can use to construct these ui components, named "login-form"
(defui LoginWindow
  (render [this {:keys [avail-letters extra-letters]}]
    (ui/grid-pane
      :alignment :center
      :hgap 10
      :vgap 10
      :padding (ui/insets
                 :bottom 25
                 :left 25
                 :right 25
                 :top 25)
      :children [(ui/text
                  :text "Full sentences please."
                   :font (ui/font
                           :family "Tahoma"
                           :weight :normal
                           :size 20)
                   :grid-pane/halignment :center
                   :grid-pane/column-index 0
                   :grid-pane/row-index 0
                   :grid-pane/column-span 2
                   :grid-pane/row-span 1)

                 (ui/separator
                  :grid-pane/column-index 0
                  :grid-pane/row-index 1
                  :grid-pane/column-span 2
                  :grid-pane/row-span 1)                 

                 (ui/label
                   :text "Sentence:"
                   :grid-pane/column-index 0
                   :grid-pane/row-index 2)

                 (ui/text-field
                  :id :sentence-field
                  :on-key-pressed {:event :sentence-changed
                                   :fn-fx/include {:sentence-field #{:text}}}
                  :min-width 335
                   :grid-pane/column-index 1
                   :grid-pane/row-index 2)

                 (ui/label
                  :text "Letter stock:"
                  :grid-pane/column-index 0
                  :grid-pane/row-index 3)

                 (ui/text
                  :id :stock-field
                  :text (apply str avail-letters)
                  :grid-pane/column-index 1
                  :grid-pane/row-index 3)

                 (ui/label
                  :text "Extra letters:"
                  :grid-pane/column-index 0
                  :grid-pane/row-index 4)

                 (ui/text
                  :id :stock-field
                  :text (apply str extra-letters)
                  :grid-pane/column-index 1
                  :grid-pane/row-index 4)

                 (ui/separator
                  :grid-pane/column-index 0
                  :grid-pane/row-index 5
                  :grid-pane/column-span 2
                  :grid-pane/row-span 1)

                 (ui/text
                  :text "Suggestions for you (###TODO###):\n* quick\n* brown\n* fox"
                  :id :suggestions
                  :grid-pane/column-index 0
                  :grid-pane/row-index 6)
                 ])))

;; Wrap our login form in a stage/scene, and create a "stage" function
(defui Stage
       (render [this args]
               (ui/stage
                 :title "JavaFX Welcome"
                 :shown true
                 :scene (ui/scene
                          :root (login-window args)))))

(defn gui-main []
  (let [;; Data State holds the business logic of our app
        data-state (atom {:avail-letters alphabet
                          :extra-letters ""})

        ;; handler-fn handles events from the ui and updates the data state
        handler-fn (fn [{:keys [event] :as all-data}]
                     (case event
                       :sentence-changed
                       (let [s (get-in all-data [:fn-fx/includes :sentence-field :text])
                             al (avail-letters s)
                             el (->> s
                                     (extra-letters)
                                     (filter (partial not= \ )))]
                         (println "Sentence changed to: " s "\n"
                                  "Available letters: " al "\n"
                                  "Extra letters: " el)
                         (swap! data-state assoc :avail-letters al :extra-letters el))
                       (println "Unknown UI event" event) all-data))

        ;; ui-state holds the most recent state of the ui
        ui-state (agent (dom/app (stage @data-state) handler-fn))]

    ;; Every time the data-state changes, queue up an update of the UI
    (add-watch data-state :ui (fn [_ _ _ _]
                                (send ui-state
                                      (fn [old-ui]
                                        (dom/update-app old-ui (stage @data-state))))))))
