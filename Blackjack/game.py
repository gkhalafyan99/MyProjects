import playing_cards

def play_game():
    # Player's initial hand
    player_hand = []
    for _ in range(0, 2):
        player_hand.append(playing_cards.deal_one_card())


    # Dealer's initial hand
    dealer_hand = []
    for _ in range(0, 2):
        dealer_hand.append(playing_cards.deal_one_card())


    print("\n---------------------- START GAME ----------------------")
    # Showing the hands
    display_hand("\nDealer's hand is", [dealer_hand[0]])
    display_hand("Player's hand is", player_hand)


    # Check for blackjack and start player_hand if necessary
    dealer_total = get_hand_total(dealer_hand)
    player_total = get_hand_total(player_hand)
    
    if dealer_total == 21 and player_total != 21:
        display_hand("\nDealer's hand is", dealer_hand)
        print("\n*** Blackjack! Dealer Wins! ***")
    elif dealer_total != 21 and player_total == 21:
        print("\n*** Blackjack! Player Wins! ***")
    elif dealer_total == 21 and player_total == 21:
        print("*** Blackjack --")
        display_hand("Dealer's hand is", dealer_hand)
        display_hand("Player's hand is", player_hand)
        print("\n*** Blackjack! Push - no winners! ***")
    elif dealer_total != 21 and player_total != 21:
        player_total = play_player_hand(player_hand)
        
        if player_total <= 21:
            dealer_total = play_dealer_hand(dealer_hand)

    
    # Checking the winner
    result = f"\n--- Dealer: {dealer_total}  Player: {player_total}  ->  "

    games["played"] += 1

    if player_total > 21:
        winner = "Dealer Wins! ---"
        games["lost"] += 1
    elif dealer_total > 21:
        winner = "Player Wins! ---"
        games["won"] += 1
    elif player_total > dealer_total:
        winner = "Player Wins! ---"
        games["won"] += 1
    elif dealer_total > player_total:
        winner = "Dealer Wins! ---"
        games["lost"] += 1
    elif dealer_total == player_total:
        winner = "Push - no winners! ---"
        games["drawn"] += 1

    print(result + winner)
    print("----------------------- END GAME -----------------------")
    print("\nThat was fun!")


def get_hand_total(hand):
    total = 0
    ace_count = 0
    for h in hand:
        if h[0] in "23456789":
            total += int(h[0])
        elif h[0] in "KQJT":
            total += 10
        elif h[0] == "A":
            ace_count += 1

    if ace_count != 0:
        if total + 10 + ace_count <= 21:
            total += (10 + ace_count)
        else:
            total += ace_count

    return total



def display_hand(hand_text, hand):
    final = hand_text + " " + str(get_hand_total(hand)) + ": "
    for h in hand:
        if h[0] == "T":
            label = "10"
        else:
            label = h[0]

        final += label + " of " + card_names[h[1]] + " | "


    print(final[:-2])


def play_player_hand(player_hand):
    answer = ""
    player_total = get_hand_total(player_hand)

    while answer != "s" and player_total <= 21:
        print("\n")
        answer = get_hit_choice()

        if answer == "h":
            player_hand.append(playing_cards.deal_one_card())
            display_hand("\nPlayer's hand is", player_hand)

        if answer == "s" and get_hand_total(player_hand) < 15:
            answer = ""
            player_hand.append(playing_cards.deal_one_card())
            print("\nCannot stand on value less than 15!")
            display_hand("\nPlayer's hand is", player_hand)

        player_total = get_hand_total(player_hand)
        if player_total > 21:
            print("--> Player busts!")
    
    return player_total


def get_hit_choice():
    answer = "-"
    while answer not in "hs":
        answer = input("Please enter h or s (h = Hit, s = Stand): ")

    return answer


def play_dealer_hand(dealer_hand):
    
    dealer_total = 0
    print("\n")
    while dealer_total < 17:
        
        display_hand("Dealer's hand is", dealer_hand)
        dealer_total = get_hand_total(dealer_hand)
        if dealer_total > 21:
            print("--> Dealer busts!")

        dealer_hand.append(playing_cards.deal_one_card())

    return dealer_total


def get_play_choice(prompt_text):
    answer = "-"
    while answer not in "yn":
        answer = input(prompt_text + " ")

    return answer


# Game begins
card_names = {
    "S": "Spades",
    "D": "Diamonds",
    "H": "Hearts",
    "C": "Clubs"
}

games = {
    "played": 0,
    "won": 0,
    "lost": 0,
    "drawn": 0
}

answer = "-"
prompt_count = 0

while answer != "n":
    if prompt_count == 0:
        answer = get_play_choice("Would you like to play BlackJack [y|n]?")
        prompt_count += 1
    else:
        answer = get_play_choice("\nPlay again [y|n]?")

    if answer == "y":
        answer = "-"
        play_game()

print(f"\nYou played {games['played']} games!")
print(f" -> Won:  {games['won']}")
print(f" -> Lost:  {games['lost']}")
print(f" -> Drawn:  {games['drawn']}")
print("\nThanks for playing! :)")