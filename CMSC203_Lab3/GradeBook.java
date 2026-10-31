
public class GradeBook
{
    private double[] scores;
    private int scoresSize;

    /**
     * Constructs a gradebook with a given capacity.
     * @param capacity maximum number of scores
     */
    public GradeBook(int capacity)
    {
        scores = new double[capacity];
        scoresSize = 0;
    }

    /**
     * Adds a score to the gradebook.
     */
    public boolean addScore(double score)
    {
        if (scoresSize < scores.length)
        {
            scores[scoresSize] = score;
            scoresSize++;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the number of scores added.
     */
    public int getScoreSize()
    {
        return scoresSize;
    }

    /**
     * Returns all scores in order, separated by spaces.
     */
    @Override
    public String toString()
    {
        String result = "";

        for (int i = 0; i < scoresSize; i++)
        {
            result += scores[i] + " ";
        }

        return result;
    }

    /**
     * Returns the sum of all scores.
     */
    public double sum()
    {
        double total = 0;

        for (int i = 0; i < scoresSize; i++)
        {
            total = total + scores[i];
        }

        return total;
    }

    /**
     * Returns the lowest score, or 0 if empty.
     */
    public double minimum()
    {
        if (scoresSize == 0)
        {
            return 0;
        }

        double smallest = scores[0];

        for (int i = 1; i < scoresSize; i++)
        {
            if (scores[i] < smallest)
            {
                smallest = scores[i];
            }
        }

        return smallest;
    }

    /**
     * Returns the sum after dropping the lowest score
     * when there are at least two scores.
     */
    public double finalScore()
    {
        if (scoresSize == 0)
        {
            return 0;
        }
        else if (scoresSize == 1)
        {
            return scores[0];
        }
        else
        {
            return sum() - minimum();
        }
    }
}
