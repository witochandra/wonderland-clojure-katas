(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (play-round [:spade 10] [:spade 9])))
  (testing "queens are higher rank than jacks"
    (is (play-round [:spade :queen] [:spade :jack])))
  (testing "kings are higher rank than queens"
    (is (play-round [:spade :king] [:spade :queen])))
  (testing "aces are higher rank than kings"
    (is (play-round [:spade :ace] [:spade :king])))
  (testing "if the ranks are equal, clubs beat spades"
    (is (play-round [:club 9] [:spade 9])))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (play-round [:diamond 9] [:club 9])))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (play-round [:heart 9] [:diamond 9]))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (play-game [[:spade 4] [:spade 5]] [[:spade 2] [:spade 3]]))
    (is (play-game [[:heart 4] [:heart 5]] [[:spade 4] [:spade 5]]))))

