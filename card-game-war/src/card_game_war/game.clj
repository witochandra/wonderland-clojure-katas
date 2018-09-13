(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def suit-values {:spade 0, :club 1, :diamond 2, :heart 3})
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def rank-values {2 2, 3 3, 4 4, 5 5, 6 6, 7 7, 8 8, 9 9, 10 10, :jack 11, :queen 12, :king 13, :ace 14})
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn play-round
  "Returns true when player1's card has higher rank"
  [player1-card player2-card]
  (let [player1-num (get rank-values (second player1-card))
        player1-suit-value ((first player1-card) suit-values)
        player2-num (get rank-values (second player2-card))
        player2-suit-value ((first player2-card) suit-values)]
    (cond (> player1-num player2-num) true
          (< player1-num player2-num) false
          (> player1-suit-value player2-suit-value) true
          (< player1-suit-value player2-suit-value) false
          :else true)))

(defn play-game
  "Returns true when player2 runs out of cards"
  [player1-cards player2-cards]
  (cond (empty? player1-cards) false
        (empty? player2-cards) true
        :else (let [player1-card (first player1-cards) player2-card (first player2-cards)]
                (if (play-round (first player1-cards) (first player2-cards))
                  (recur (concat (rest player1-cards) `(player1-card player2-card)) (rest player2-cards))
                  (recur (rest player1-cards) (concat (rest player2-cards) `(player2-card player1-card)))))))
