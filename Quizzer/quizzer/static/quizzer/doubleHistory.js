document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("#singleHistoryForm").onsubmit = () => {
        const player1 = document.querySelector("#player1").value
        const player2 = document.querySelector("#player2").value
        
        fetch(`/doubleScores/${player1}/${player2}`)
        .then(response => response.json())
        .then(scores => {
            
            let table = document.querySelector("#singleScores");
            table.innerHTML = "";

            const firtsRow = document.createElement("tr");

            const th1 = document.createElement("th");
            const th2 = document.createElement("th");
            const th3 = document.createElement("th");
            const th4 = document.createElement("th");
            const th5 = document.createElement("th");

            th1.innerHTML = "Player1";
            th2.innerHTML = "Score";
            th3.innerHTML = "Player2";
            th4.innerHTML = "Score";
            th5.innerHTML = "Winner"


            firtsRow.append(th1);
            firtsRow.append(th2);
            firtsRow.append(th3);
            firtsRow.append(th4);  
            firtsRow.append(th5);     
            table.append(firtsRow);

            scores.forEach(function(val) {
                const row = document.createElement("tr");

                const td1 = document.createElement("td");
                const td2 = document.createElement("td");
                const td3 = document.createElement("td");
                const td4 = document.createElement("td");
                const td5 = document.createElement("td");

                td1.innerHTML = val.player1;
                td2.innerHTML = val.player1score;
                td3.innerHTML = val.player2;
                td4.innerHTML = val.player2score;
                td5.innerHTML = val.winner;

                row.append(td1);
                row.append(td2);
                row.append(td3);
                row.append(td4);
                row.append(td5);

                table.append(row);
            });

        });

        return false;
    }
});