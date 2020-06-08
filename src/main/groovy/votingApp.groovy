import groovy.json.JsonBuilder


class Votation {

    public static def makeYourVote() {
        def votesList = [:]

        while (votesList.size() < 10) {
            println("Please Enter your candidate's name: ")
            String candidateName = System.in.newReader().readLine()
            if (candidateName?.trim()) {
                println("Your vote for ${candidateName} accepted!")
                if (votesList.containsKey(candidateName)) {
                    int voteCount = votesList.getAt(candidateName)
                    votesList.put(candidateName, voteCount + 1)
                } else {
                    votesList.put(candidateName, 1)
                }
            } else {
                println("Please enter a valid candidate name!")
            }

            def resultsFile = new JsonBuilder(votesList).toPrettyString()
            try {
                new File("./votingResults.json").write(resultsFile)
            } catch (FileNotFoundException fne) {
                println("Failed to open file votingResults.json. Reason: ${fne}")
            } catch (Exception e) {
                println("Failed to write to results file! ${e}")
            }

        }
    }

    public static void main(String[] args) {
        Votation.makeYourVote()
    }
}

