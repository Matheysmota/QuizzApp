package com.matheus.mota.quizapp.fragments.quizzgame

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.matheus.mota.quizapp.R
import com.matheus.mota.quizapp.databinding.FragmentQuizzGameBinding
import com.matheus.mota.quizapp.model.data.DataQuizzGameLevel


@Suppress("DEPRECATION")
class QuizzGameFragment : Fragment() {

    private lateinit var _binding: FragmentQuizzGameBinding
    private val binding get() = _binding
    private var level = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizzGameBinding.inflate(inflater, container, false)
        val view = binding.root
        // implementations
        setListeners()
        startUiFirstLevel()
        startCountDown()

        return view
    }
    // Captures click events and throws changeQuizz parameters
    private fun setListeners() {
        binding.run {
            firstAnswerButton.setOnClickListener {
                val text = firstAnswerButton.text.toString()
                val firstButton = firstAnswerButton
                changeQuizz(level, text, button = firstButton)
            }
            secondAnswerButton.setOnClickListener {
                val text = secondAnswerButton.text.toString()
                val secondButton = secondAnswerButton
                changeQuizz(level, text, button = secondButton)

            }
            thirdAnswerButton.setOnClickListener {
                val text = thirdAnswerButton.text.toString()
                val thirdButton = thirdAnswerButton
                changeQuizz(level, text, button = thirdButton)
            }
            fourthAnswerButton.setOnClickListener {
                val text = fourthAnswerButton.text.toString()
                val fourthButton = fourthAnswerButton
                changeQuizz(level, text, fourthButton)
            }
        }
    }

    private fun startUiFirstLevel() {
        val change = DataQuizzGameLevel()
        // declarations
        val question = change.firstLevelQuestion
        val firstAnswer = change.firstLevelAnswerOne
        val secondAnswer = change.firstLevelAnswerTwo
        val thirdAnswer = change.firstLevelAnswerThree
        val fourthAnswer = change.firstLevelAnswerFour
        binding.run {
            //appearance and text change
            "Level 1 of 8".also { levelCountTextView.text = it }
            "Question 1".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
    private fun startCountDown() {
        var SECONDS_AMOUNT = 256L
        val finishCountDown = 0L
        object : CountDownTimer(256000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countDownTextView.text = SECONDS_AMOUNT.toString()
                SECONDS_AMOUNT--
            }
            override fun onFinish() {
                binding.countDownTextView.text = finishCountDown.toString()
                gameOver()
            }
        }.start()
    }
    // Implement screen switching's validations
    private fun changeQuizz(level: Int, correctAnswer: String, button: Button) {
        val change = DataQuizzGameLevel()
        when (level) {
            1 -> {
                if (correctAnswer == change.firstLevelCorrectAnswer) {
                    changeButtonColor(true, button)
                    this.level++
                    Handler().postDelayed({ changeUiSecondLevel() }, 1200)
                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
            2 -> {
                if (correctAnswer == change.secondLevelCorrectAnswer) {
                    changeButtonColor(true, binding.thirdAnswerButton)
                    this.level++
                    Handler().postDelayed({ changeUiThirdLevel() }, 1200)
                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
            3 -> {
                if (correctAnswer == change.thirdLevelCorrectAnswer) {
                    changeButtonColor(true, binding.secondAnswerButton)
                    this.level++
                    Handler().postDelayed({ changeUiFourthLevel() }, 1200)

                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
            4 -> {
                if (correctAnswer == change.fourthLevelCorrectAnswer) {
                    changeButtonColor(true, binding.fourthAnswerButton)
                    this.level++
                    Handler().postDelayed({ changeUiFifthLevel() }, 1200)
                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
            5 -> {
                if (correctAnswer == change.fifthLevelCorrectAnswer) {
                    changeButtonColor(true, binding.firstAnswerButton)
                    this.level++
                    Handler().postDelayed({ changeUiSixthLevel() }, 1200)
                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
            6 -> {
                if (correctAnswer == change.sixthLevelCorrectAnswer) {
                    changeButtonColor(true, binding.secondAnswerButton)
                    this.level++
                    Handler().postDelayed({ changeUiSeventhLevel() }, 1200)
                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
            7 -> {
                if (correctAnswer == change.seventhLevelCorrectAnswer) {
                    changeButtonColor(true, binding.fourthAnswerButton)
                    this.level++
                    Handler().postDelayed({ changeUiEighthLevel() }, 1200)
                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
            8 -> {
                if (correctAnswer == change.eighthLevelCorrectAnswer) {
                    changeButtonColor(true, binding.thirdAnswerButton)
                    this.level++
                    Handler().postDelayed({ youWin() }, 1200)

                } else {
                    changeButtonColor(false, button)
                    gameOver()
                }
            }
        }
    }

    private fun changeButtonColor(answer: Boolean, button: Button){
        if (answer){
            button.setBackgroundColor(resources.getColor(R.color.true_color))
            Handler().postDelayed({
                button.setBackgroundColor(resources.getColor(R.color.background_splash_screen))
            }, 1200)

        } else {
            button.setBackgroundColor(resources.getColor(R.color.false_color))
        }
    }
    // Game status
    private fun gameOver() {
        Toast.makeText(requireContext(), "You lost, try again", 50L.toInt()).show()
        Handler().postDelayed({
            findNavController().navigate(R.id.action_quizzGameFragment_to_gameOverFragment)
            onStop()
        }, 1500)
    }
    private fun youWin() {
        Toast.makeText(requireContext(), "You Win!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_quizzGameFragment_to_gameWinFragment)
    }
    // Levels
    private fun changeUiSecondLevel() {
        Toast.makeText(requireContext(), "You're right!", Toast.LENGTH_SHORT).show()
        val change = DataQuizzGameLevel()
        val question = change.secondLevelQuestion
        val firstAnswer = change.secondLevelAnswerOne
        val secondAnswer = change.secondLevelAnswerTwo
        val thirdAnswer = change.secondLevelAnswerThree
        val fourthAnswer = change.secondLevelAnswerFour

        binding.run {
            //appearance and text change
            emojiImageView.setImageResource(R.drawable.emoji_quizz_two)
            "Level 2 of 8".also { levelCountTextView.text = it }
            "Question 2".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
    private fun changeUiThirdLevel() {
        Toast.makeText(requireContext(), "You're right!", Toast.LENGTH_SHORT).show()
        val change = DataQuizzGameLevel()
        // declarations
        val question = change.thirdLevelQuestion
        val firstAnswer = change.thirdLevelAnswerOne
        val secondAnswer = change.thirdLevelAnswerTwo
        val thirdAnswer = change.thirdLevelAnswerThree
        val fourthAnswer = change.thirdLevelAnswerFour

        binding.run {
            //appearance and text change
            emojiImageView.setImageResource(R.drawable.emoji_quizz_three)
            "Level 3 of 8".also { levelCountTextView.text = it }
            "Question 3".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
    private fun changeUiFourthLevel() {
        Toast.makeText(requireContext(), "You're right!", Toast.LENGTH_SHORT).show()
        val change = DataQuizzGameLevel()
        // declarations
        val question = change.fourthLevelQuestion
        val firstAnswer = change.fourthLevelAnswerOne
        val secondAnswer = change.fourthLevelAnswerTwo
        val thirdAnswer = change.fourthLevelAnswerThree
        val fourthAnswer = change.fourthLevelAnswerFour

        binding.run {
            //appearance and text change
            emojiImageView.setImageResource(R.drawable.emoji_quizz_four)
            "Level 4 of 8".also { levelCountTextView.text = it }
            "Question 4".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
    private fun changeUiFifthLevel() {
        Toast.makeText(requireContext(), "You're right!", Toast.LENGTH_SHORT).show()
        val change = DataQuizzGameLevel()
        // declarations
        val question = change.fifthLevelQuestion
        val firstAnswer = change.fifthLevelAnswerOne
        val secondAnswer = change.fifthLevelAnswerTwo
        val thirdAnswer = change.fifthLevelAnswerThree
        val fourthAnswer = change.fifthLevelAnswerFour

        binding.run {
            //appearance and text change
            emojiImageView.setImageResource(R.drawable.emoji_quizz_five)
            "Level 5 of 8".also { levelCountTextView.text = it }
            "Question 5".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
    private fun changeUiSixthLevel() {
        Toast.makeText(requireContext(), "You're right!", Toast.LENGTH_SHORT).show()
        val change = DataQuizzGameLevel()
        // declarations
        val question = change.sixthLevelQuestion
        val firstAnswer = change.sixthLevelAnswerOne
        val secondAnswer = change.sixthLevelAnswerTwo
        val thirdAnswer = change.sixthLevelAnswerThree
        val fourthAnswer = change.sixthLevelAnswerFour

        binding.run {
            //appearance and text change
            emojiImageView.setImageResource(R.drawable.emoji_quizz_six)
            "Level 6 of 8".also { levelCountTextView.text = it }
            "Question 6".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
    private fun changeUiSeventhLevel() {
        Toast.makeText(requireContext(), "You're right!", Toast.LENGTH_SHORT).show()
        val change = DataQuizzGameLevel()
        // declarations
        val question = change.seventhLevelQuestion
        val firstAnswer = change.seventhLevelAnswerOne
        val secondAnswer = change.seventhLevelAnswerTwo
        val thirdAnswer = change.seventhLevelAnswerThree
        val fourthAnswer = change.seventhLevelAnswerFour

        binding.run {
            //appearance and text change
            emojiImageView.setImageResource(R.drawable.emoji_quizz_seven)
            "Level 7 of 8".also { levelCountTextView.text = it }
            "Question 7".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
    private fun changeUiEighthLevel() {
        Toast.makeText(requireContext(), "You're right!", Toast.LENGTH_SHORT).show()
        val change = DataQuizzGameLevel()
        // declarations
        val question = change.eighthLevelQuestion
        val firstAnswer = change.eighthLevelAnswerOne
        val secondAnswer = change.eighthLevelAnswerTwo
        val thirdAnswer = change.eighthLevelAnswerThree
        val fourthAnswer = change.eighthLevelAnswerFour

        binding.run {
            //appearance and text change
            emojiImageView.setImageResource(R.drawable.emoji_quizz_eigth)
            "Level 8 of 8".also { levelCountTextView.text = it }
            "Question 8".also { questionLevel.text = it }
            // Change texts
            questionTextView.text = question
            firstAnswerButton.text = firstAnswer
            secondAnswerButton.text = secondAnswer
            thirdAnswerButton.text = thirdAnswer
            fourthAnswerButton.text = fourthAnswer
        }
    }
}





