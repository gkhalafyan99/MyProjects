document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("#singleLeaderForm").onsubmit = () => {
        const subject = document.querySelector("#singleLeaderSelect").value
        
        fetch(`/singleLeaders/${subject}`)
        .then(response => response.json())
        .then(leaders => {
            
            let table = document.querySelector("#singleLeaders");
            table.innerHTML = "";

            const firtsRow = document.createElement("tr");

            const th1 = document.createElement("th");
            const th2 = document.createElement("th");
            const th3 = document.createElement("th");
            const th4 = document.createElement("th");

            th1.innerHTML = "User";
            th2.innerHTML = "Possible Points";
            th3.innerHTML = "Gained Points";
            th4.innerHTML = "Percentage";


            firtsRow.append(th1);
            firtsRow.append(th2);
            firtsRow.append(th3);
            firtsRow.append(th4);            
            table.append(firtsRow);

            leaders.forEach(function(val) {
                const row = document.createElement("tr");

                const td1 = document.createElement("td");
                const td2 = document.createElement("td");
                const td3 = document.createElement("td");
                const td4 = document.createElement("td");

                td1.innerHTML = val.username;

                if (subject === "overall") {
                    td2.innerHTML = val.overallPossibleScore;
                    td3.innerHTML = val.overallGainedScore;
                    td4.innerHTML = val.overallPercent;
                } else if (subject === "math") {
                    td2.innerHTML = val.mathPossibleScore;
                    td3.innerHTML = val.mathGainedScore;
                    td4.innerHTML = val.mathPercent;
                } else if (subject === "history") {
                    td2.innerHTML = val.historyPossibleScore;
                    td3.innerHTML = val.historyGainedScore;
                    td4.innerHTML = val.historyPercent;
                } else if (subject === "geography") {
                    td2.innerHTML = val.geographyPossibleScore;
                    td3.innerHTML = val.geographyGainedScore;
                    td4.innerHTML = val.geographyPercent;
                } else if (subject === "computers") {
                    td2.innerHTML = val.computersPossibleScore;
                    td3.innerHTML = val.computersGainedScore;
                    td4.innerHTML = val.computersPercent;
                } else if (subject === "biology") {
                    td2.innerHTML = val.biologyPossibleScore;
                    td3.innerHTML = val.biologyGainedScore;
                    td4.innerHTML = val.biologyPercent;
                } else if (subject === "literature") {
                    td2.innerHTML = val.literaturePossibleScore;
                    td3.innerHTML = val.literatureGainedScore;
                    td4.innerHTML = val.literaturePercent;
                } else if (subject === "mixed") {
                    td2.innerHTML = val.mixedPossibleScore;
                    td3.innerHTML = val.mixedGainedScore;
                    td4.innerHTML = val.mixedPercent;
                }
                

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