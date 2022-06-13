document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("#singleHistoryForm").onsubmit = () => {
        const user = document.querySelector("#singleHistorySelect").value
        
        fetch(`/singleScores/${user}`)
        .then(response => response.json())
        .then(scores => {
            
            let table = document.querySelector("#singleScores");
            table.innerHTML = "";

            const firtsRow = document.createElement("tr");

            const th1 = document.createElement("th");
            const th2 = document.createElement("th");
            const th3 = document.createElement("th");
            const th4 = document.createElement("th");

            th1.innerHTML = "User";
            th2.innerHTML = "Subject";
            th3.innerHTML = "Score";
            th4.innerHTML = "Date";


            firtsRow.append(th1);
            firtsRow.append(th2);
            firtsRow.append(th3);
            firtsRow.append(th4);            
            table.append(firtsRow);

            scores.forEach(function(val) {
                const row = document.createElement("tr");

                const td1 = document.createElement("td");
                const td2 = document.createElement("td");
                const td3 = document.createElement("td");
                const td4 = document.createElement("td");

                td1.innerHTML = val.username;
                td2.innerHTML = val.subject;
                td3.innerHTML = val.score;
                td4.innerHTML = val.time;

                row.append(td1);
                row.append(td2);
                row.append(td3);
                row.append(td4);

                table.append(row);
            });

        });

        return false;
    }
});